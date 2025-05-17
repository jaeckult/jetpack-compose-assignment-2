package com.example.todo.data.remote
import retrofit2.http.GET
import com.example.todo.data.remote.TodoDto
interface ApiServiceInterface {
    @GET("todoList")
    suspend fun getTodos(): List<TodoDto>
}