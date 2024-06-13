package com.c6.note.application.request

data class NoteCreateRequest(
        val student: StudentRequest,
        val note: Int
){
}
