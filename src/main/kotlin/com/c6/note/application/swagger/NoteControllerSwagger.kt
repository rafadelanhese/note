package com.c6.note.application.swagger

import com.c6.note.application.request.NoteCreateRequest
import com.c6.note.application.response.NoteResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

interface NoteControllerSwagger {
    @Operation(summary = "Insert notes of student ", description = "Retunsrs 200 if sucessful")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successful Operation")
        ]
    )

    fun insertNotes(
        @RequestBody noteCreate: NoteCreateRequest
    ): ResponseEntity<Void>

    fun getAllNotes(): ResponseEntity<List<NoteResponse>>
}