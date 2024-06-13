package com.c6.note.domain.service

import com.c6.note.application.request.NoteCreateRequest
import com.c6.note.domain.model.Note

interface NoteService {
    fun insertNotes(noteCreate: NoteCreateRequest)
    fun getAllNotes(): MutableIterable<Note>
}