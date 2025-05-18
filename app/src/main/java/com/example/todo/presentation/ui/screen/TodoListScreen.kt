package com.example.todo.presentation.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.todo.domain.model.Todo
import com.example.todo.presentation.viewmodel.TodoListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(viewModel: TodoListViewModel, navController: NavController) {
    val todos by viewModel.todos.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTodos()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My TodoList") },
//                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            if (error != null) {
                Text(
                    text = error ?: "",
                    color = Color.Red,
                    modifier = Modifier.padding(8.dp)
                )
            }
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(todos) { todo ->
                    TodoListItem(todo) {
                        navController.navigate("detail/${todo.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = if (todo.completed) Color(0xFF86FF8B) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = todo.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (todo.completed) "Completed" else "Pending",
                    style = MaterialTheme.typography.bodySmall,
                    color = if (todo.completed) Color(0xFF388E3C) else Color(0xFF2D00F5)
                )
            }

            Icon(
                imageVector = if (todo.completed) Icons.Default.CheckCircle else Icons.Filled.Close,
                contentDescription = null,
                tint = if (todo.completed) Color(0xFFD2B316) else Color(0xFFFF0000)
            )
        }
    }
}