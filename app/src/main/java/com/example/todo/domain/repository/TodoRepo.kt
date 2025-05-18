package com.example.todo.domain.repository

import com.example.todo.data.local.TodoDao
import com.example.todo.data.local.toDomain
import com.example.todo.data.remote.ApiServiceInterface
import com.example.todo.domain.model.Todo
import com.example.todo.data.remote.toEntity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import android.util.Log

class TodoRepository @Inject constructor(
    private val apiService: ApiServiceInterface,
    private val DAO: TodoDao
) {
    suspend fun getTodos(): List<Todo> = withContext(Dispatchers.IO) {
        try {
            val remoteTodos = apiService.getTodos()
            Log.d("TodoRepository", "Fetched todos from API: ${remoteTodos.size}")
            val todoEntities = remoteTodos.map { it.toEntity() }
            DAO.insertAll(todoEntities)
            todoEntities.map { it.toDomain() }
        } catch (e: Exception) {
            Log.e("TodoRepository", "Failed to fetch from API: $e")
            DAO.getTodos().map { it.toDomain() }
        }
    }

    suspend fun getTodoById(id: Int): Todo? = withContext(Dispatchers.IO) {
        DAO.getTodoById(id)?.toDomain()
    }


}