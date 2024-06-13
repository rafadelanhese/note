package com.c6.note.resource.infrastructure

interface SQSEventPublisher {
    fun publishMessage(message: String)
}