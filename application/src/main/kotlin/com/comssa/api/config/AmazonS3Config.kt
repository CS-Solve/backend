package com.comssa.api.config

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AmazonS3Config(
	@Value("\${cloud.aws.credentials.access-key}")
	private var accessKey: String,
	@Value("\${cloud.aws.credentials.secret-key}")
	private val secretKey: String,
	@Value("\${cloud.aws.region.static}")
	private val region: String,
) {
	@Bean
	fun amazonS3Client(): AmazonS3 {
		val credentials: AWSCredentials = BasicAWSCredentials(accessKey, secretKey)
		return AmazonS3ClientBuilder
			.standard()
			.withCredentials(AWSStaticCredentialsProvider(credentials))
			.withRegion(region)
			.build()
	}
}
