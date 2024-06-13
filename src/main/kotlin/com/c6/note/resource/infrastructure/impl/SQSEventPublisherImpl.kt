package com.c6.note.resource.infrastructure.impl

import com.amazonaws.services.sqs.AmazonSQS
import com.amazonaws.services.sqs.model.SendMessageRequest
import com.c6.note.resource.infrastructure.SQSEventPublisher
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class SQSEventPublisherImpl(private val amazonSQS: AmazonSQS): SQSEventPublisher {

    @Value("\${aws.sqs.queue}")
    lateinit var queueUrl: String

    override fun publishMessage(message: String) {
        try {
            val messageRequest = SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(message)
            println(String.format("Publising message: %s", message))
            amazonSQS.sendMessage(messageRequest)
        }catch (exception: Exception){
            exception.printStackTrace()
        }
    }
}