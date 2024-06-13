package com.c6.note.application.controller

import com.c6.note.application.request.NoteCreateRequest
import com.c6.note.application.response.NoteResponse
import com.c6.note.application.swagger.NoteControllerSwagger
import com.c6.note.domain.model.Note
import com.c6.note.domain.service.NoteService
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/note")
class NoteController(private val noteService: NoteService) : NoteControllerSwagger {


    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun insertNotes(
        @RequestBody noteCreate: NoteCreateRequest
    ): ResponseEntity<Void> {
        noteService.insertNotes(noteCreate)
        return ResponseEntity.ok().build()
    }

    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    override fun getAllNotes(): ResponseEntity<List<NoteResponse>>{
        val allNotes = noteService.getAllNotes().map { note -> NoteResponse(note.id, note.note, note.studentId)  }
        return ResponseEntity.ok(allNotes)
    }
}