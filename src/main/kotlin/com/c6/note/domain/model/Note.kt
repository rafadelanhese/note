package com.c6.note.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id

@Entity(name = "note")
class Note(

    @Id
    @GeneratedValue
    val id: Long?,

    @Column(name = "note")
    val note: Int,

    @Column(name = "student_id")
    val studentId: Long

){
}