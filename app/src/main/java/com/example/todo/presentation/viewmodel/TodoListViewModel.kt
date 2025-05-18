package com.example.todo.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.domain.model.Todo
import com.example.todo.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
) : ViewModel() {

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchTodos()
    }

    fun fetchTodos() {
        viewModelScope.launch {
            try {
                _todos.value = repository.getTodos()
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
    suspend fun getTodoById(id: Int): Todo? {
        return repository.getTodoById(id)
    }
}