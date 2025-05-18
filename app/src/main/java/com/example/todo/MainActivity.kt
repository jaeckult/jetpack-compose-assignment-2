package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.presentation.ui.screen.TodoListScreen
import com.example.todo.presentation.ui.screen.TodoDetailScreen
import com.example.todo.presentation.viewmodel.TodoListViewModel
import com.example.todo.MyApp
import com.example.todo.presentation.theme.TodoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TodoTheme {
                val navController = rememberNavController()
                val viewModel: TodoListViewModel = viewModel()

                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        TodoListScreen(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                    composable("detail/{todoId}") { backStackEntry ->
                        val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
                        if (todoId != null) {
                            TodoDetailScreen(
                                todoId = todoId,
                                viewModel = viewModel,

                                navController = navController
                            )
                        } else {
                        }
                    }
                }
            }
        }
    }
}