package com.study.computerscience.config;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.Slf4jLogger;
import com.sun.istack.NotNull;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PreDestroy;
import java.util.Properties;

import static java.lang.System.exit;

@Slf4j
@Profile("local")
@Component
@ConfigurationProperties(prefix = "ssh") // yml의 내용을 자동으로 필드에 주입하기 위한 Prefix
@Validated
@Setter //자동 주입
public class SshTunnelingInitializer {
	@NotNull
	private String remoteJumpHost;
	@NotNull
	private String user;
	@NotNull
	private int sshPort;
	@NotNull
	private String privateKey;
	@NotNull
	private int databasePort;
	@NotNull
	private String databaseEndPoint;

	private Session session;

	@PreDestroy
	public void closeSsh() {
		if (session.isConnected()) {
			session.disconnect();
		}

	}

	public Integer buildSshConnection() {

		Integer forwardedPort = null;

		try {
			log.info("{}@{}:{} -> {}:{} with privateKey",
				user,
				remoteJumpHost,
				sshPort,
				databaseEndPoint,
				databasePort);

			log.info("start ssh tunneling..");
			JSch jSch = new JSch();
			jSch.setInstanceLogger(new Slf4jLogger());

			log.info("creating ssh session");
			jSch.addIdentity(privateKey);  // 개인키

			session = jSch.getSession(user, remoteJumpHost, sshPort);  // 세션 설정
			Properties config = new Properties();
			// 서버 호스트 키와 허용된 공개 키 알고리즘에 ssh-rsa 추가
			// JSch.setConfig("server_host_key", JSch.getConfig("server_host_key") + ",ssh-rsa");
			// JSch.setConfig("PubkeyAcceptedAlgorithms", JSch.getConfig("PubkeyAcceptedAlgorithms") + ",ssh-rsa");
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			log.info("complete creating ssh session");

			log.info("start connecting ssh connection");
			session.connect();  // ssh 연결
			log.info("success connecting ssh connection ");

			// 로컬pc의 남는 포트 하나와 원격 접속한 pc의 db포트 연결
			log.info("start forwarding");
			forwardedPort = session.setPortForwardingL(0, databaseEndPoint, databasePort);
			log.info("Port forwarding created on local port {} to remote port {}", forwardedPort, databasePort);

		} catch (Exception e) {
			log.error("fail to make ssh tunneling");
			this.closeSsh();
			exit(1);
		}

		return forwardedPort;
	}

}
