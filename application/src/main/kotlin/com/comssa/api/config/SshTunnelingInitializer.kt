package com.comssa.api.config

import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import com.jcraft.jsch.Slf4jLogger
import mu.KotlinLogging
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.validation.annotation.Validated
import java.util.Properties
import javax.annotation.PreDestroy

@Profile("local")
@Component
@ConfigurationProperties(prefix = "ssh") // yml의 내용을 자동으로 필드에 주입하기 위한 Prefix
// yml의 내용을 자동으로 필드에 주입하기 위한 Prefix
@Validated
class SshTunnelingInitializer {
	private var log = KotlinLogging.logger {}
	lateinit var remoteJumpHost: String
	lateinit var user: String
	var sshPort: Int = 0
	var databasePort: Int = 5432
	lateinit var privateKey: String
	lateinit var databaseEndPoint: String
	var session: Session? = null

	@PreDestroy
	fun closeSsh() {
		if (session?.isConnected == true) {
			session?.disconnect()
		}
	}

	fun buildSshConnection(): Int? {
		var forwardedPort: Int? = null

		try {
			log.info(
				"{}@{}:{} -> {}:{} with privateKey",
				user,
				remoteJumpHost,
				sshPort,
				databaseEndPoint,
				databasePort,
			)
			log.info("start ssh tunneling..")
			val jSch = JSch()
			jSch.instanceLogger = Slf4jLogger()

			log.info("creating ssh session")
			jSch.addIdentity(privateKey) // 개인키

			session = jSch.getSession(user, remoteJumpHost, sshPort) // 세션 설정
			val config = Properties()
			// 서버 호스트 키와 허용된 공개 키 알고리즘에 ssh-rsa 추가
			// JSch.setConfig("server_host_key", JSch.getConfig("server_host_key") + ",ssh-rsa");
			// JSch.setConfig("PubkeyAcceptedAlgorithms", JSch.getConfig("PubkeyAcceptedAlgorithms") + ",ssh-rsa");
			config["StrictHostKeyChecking"] = "no"
			session!!.setConfig(config)
			log.info("complete creating ssh session")

			log.info("start connecting ssh connection")
			session!!.connect() // ssh 연결
			log.info("success connecting ssh connection ")

			// 로컬pc의 남는 포트 하나와 원격 접속한 pc의 db포트 연결
			log.info("start forwarding")
			forwardedPort = session!!.setPortForwardingL(0, databaseEndPoint, databasePort)
			log.info(
				"Port forwarding created on local port {} to remote port {}",
				forwardedPort,
				databasePort,
			)
		} catch (e: Exception) {
			log.error("fail to make ssh tunneling")
			this.closeSsh()
			System.exit(1)
		}

		return forwardedPort
	}
}
