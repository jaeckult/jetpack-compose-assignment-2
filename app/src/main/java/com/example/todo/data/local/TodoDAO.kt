package com.example.todo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todoList")
    suspend fun getTodos(): List<TodoEntity>

    @Query("SELECT * FROM todoList WHERE id = :id")
    suspend fun getTodoById(id: Int): TodoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todos: List<TodoEntity>)
}