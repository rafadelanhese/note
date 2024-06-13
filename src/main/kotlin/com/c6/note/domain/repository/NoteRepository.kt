package com.c6.note.domain.repository

import com.c6.note.domain.model.Note
import jakarta.transaction.Transactional
import org.springframework.data.repository.CrudRepository

@Transactional
interface NoteRepository : CrudRepository<Note, Long> {
}