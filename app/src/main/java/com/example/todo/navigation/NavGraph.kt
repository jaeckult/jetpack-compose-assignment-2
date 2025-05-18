//package com.example.todo.navigation
//
//import androidx.compose.runtime.Composable
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import com.example.todo.presentation.ui.screen.TodoDetailScreen
//import com.example.todo.presentation.ui.screen.TodoListScreen
//import androidx.navigation.NavType
//import androidx.navigation.navArgument
//import androidx.compose.runtime.LaunchedEffect
//import com.example.todo.presentation.viewmodel.TodoListViewModel
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.lifecycle.viewmodel.compose.viewModel
//import com.example.todo.presentation.ui.screen.TodoDetailScreen
//import com.example.todo.presentation.ui.screen.TodoListScreen
//
//@Composable
//fun AppNavHost(navController: NavHostController) {
//    NavHost(
//        navController = navController,
//        startDestination = "todo/listScreen"
//    ) {
//        composable("detail/{todoId}") { backStackEntry ->
//            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
//            val viewModel: TodoListViewModel = hiltViewModel()
//            if (todoId != null) {
//                TodoDetailScreen(
//                    todoId = todoId,
//                    viewModel = viewModel,
//                    navController = navController
//                )
//            } else {
//                // Handle null ID case
//            }
//        }
//        composable(
//            route = "todo/detailScreen/{todoId}",
//            arguments = listOf(navArgument("todoId") { type = NavType.IntType })
//        ) { backStackEntry ->
//            val todoId = backStackEntry.arguments?.getInt("todoId") ?: 0
//            val viewModel: TodoListViewModel = hiltViewModel()
//
//            LaunchedEffect(todoId) {
//                viewModel.handleEvent(DetailScreenEvent.OnIdChange(todoId))
//                viewModel.handleEvent(DetailScreenEvent.FetchTodo)
//            }
//
//            TodoDetailScreen(
//                TodoListViewModel = viewModel,
//                onBack = { navController.popBackStack() }
//            )
//        }
//    }
//}