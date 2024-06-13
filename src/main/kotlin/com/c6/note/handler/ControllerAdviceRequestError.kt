package com.c6.note.handler

import com.c6.note.exception.StudentNotFoundException
import com.c6.note.handler.model.ErrorDetail
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDate

@ControllerAdvice
class ControllerAdviceRequestError : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(StudentNotFoundException::class)])
    fun handlerStudentNotExists(exception: StudentNotFoundException, request: WebRequest): ResponseEntity<ErrorDetail>{
        val error = ErrorDetail("Student not found handler", exception.message!!, LocalDate.now())
        return ResponseEntity(error, HttpStatus.NOT_FOUND)

    }
}