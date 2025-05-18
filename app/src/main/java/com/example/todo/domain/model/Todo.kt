package com.example.todo.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id : Int,
    val userId : Int,
    val title : String,
    val completed : Boolean
)