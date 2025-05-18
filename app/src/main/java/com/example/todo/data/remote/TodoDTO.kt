package com.example.todo.data.remote

import com.example.todo.data.local.TodoEntity
import com.example.todo.domain.model.Todo
import kotlinx.serialization.Serializable

// data/remote/TodoDto.kt
@Serializable
data class TodoDto(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean
) {
    fun toTodo(): Todo = Todo(id, userId, title, completed)
}
fun TodoDto.toEntity(): TodoEntity = TodoEntity(
    id = id,
    userId = userId,
    title = title,
    completed = completed
)