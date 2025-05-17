package com.example.todo.data.local
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todo.domain.model.Todo

@Entity(tableName = "todoList")
data class TodoEntity(
    @PrimaryKey val id: Int,
    val userId: Int,
    val title: String,
    val completed: Boolean
)
fun TodoEntity.toDomain(): Todo = Todo(id, userId, title, completed)