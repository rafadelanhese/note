package com.c6.note.config

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.sqs.AmazonSQSAsync
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class SQSConfig {

    @Value("\${cloud.aws.region.static}")
    lateinit var region: String

    @Value("\${cloud.aws.credentials.access-key}")
    lateinit var accessKey: String

    @Value("\${cloud.aws.credentials.secret-key}")
    lateinit var secretKey: String

    @Value("\${cloud.aws.endpoint.uri}")
    lateinit var sqsUrl: String

    @Bean
    @Primary
    fun amazonSQSAsync(): AmazonSQSAsync {
        return AmazonSQSAsyncClientBuilder
            .standard()
            .withEndpointConfiguration(
                AwsClientBuilder.EndpointConfiguration(sqsUrl, region)
            )
            .withCredentials(
                AWSStaticCredentialsProvider(
                    BasicAWSCredentials(accessKey, secretKey)
                )
            )
            .build()
    }

    @Bean
    fun queueMessagingTemplate(): QueueMessagingTemplate{
        return QueueMessagingTemplate(amazonSQSAsync())
    }

    @Bean
    fun objectMapper(): ObjectMapper{
        return ObjectMapper()
    }
}