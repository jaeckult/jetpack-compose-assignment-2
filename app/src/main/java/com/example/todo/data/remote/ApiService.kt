package com.example.todo.data.remote
import retrofit2.http.GET
import com.example.todo.data.remote.TodoDto
import retrofit2.http.Path

interface ApiServiceInterface {
    @GET("todos")
    suspend fun getTodos(): List<TodoDto>

    @GET("todos/{id}")
    suspend fun getTodoById(@Path("id") id: Int): TodoDto
}