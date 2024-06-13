package com.c6.note.domain.service.impl

import com.c6.note.application.request.NoteCreateRequest
import com.c6.note.domain.model.Note
import com.c6.note.domain.repository.NoteRepository
import com.c6.note.domain.service.NoteService
import com.c6.note.resource.infrastructure.SQSEventPublisher
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl(private val noteRepository: NoteRepository, private val sqsEventPublisher: SQSEventPublisher) : NoteService {

    val mapper = ObjectMapper()

    override fun insertNotes(noteCreate: NoteCreateRequest) {
        val studentNote = Note(null, noteCreate.note, noteCreate.student.id)
        noteRepository.save(studentNote)
        sendMessageNotification(noteCreate)
    }

    override fun getAllNotes(): MutableIterable<Note> {
        return noteRepository.findAll()
    }

    fun sendMessageNotification(noteCreate: NoteCreateRequest){
        val message = mapper.writeValueAsString(noteCreate)
        sqsEventPublisher.publishMessage(message)
    }
}