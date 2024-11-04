package com.example.biblio

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.biblio.ui.theme.BiblioTheme
import com.example.biblio.ui.view.BookListScreen
import com.example.biblio.ui.view.MainScreen
import com.example.biblio.ui.viewmodel.MainViewModel
import android.os.Bundle
import androidx.compose.runtime.Composable
import com.example.biblio.data.repository.DataRepository
import com.example.biblio.domain.GetAllBooksUseCase
import com.example.biblio.domain.ReadDataUseCase
import com.example.domain.DeleteDataUseCase
import com.example.domain.InsertDataUseCase
import com.example.domain.UpdateDataUseCase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = DataRepository(this)
        val insertDataUseCase = InsertDataUseCase(repository)
        val updateDataUseCase = UpdateDataUseCase(repository)
        val deleteDataUseCase = DeleteDataUseCase(repository)
        val readDataUseCase = ReadDataUseCase(repository)
        val getAllBooksUseCase = GetAllBooksUseCase(repository)

        val mainViewModel = MainViewModel(
            insertDataUseCase,
            updateDataUseCase,
            deleteDataUseCase,
            readDataUseCase,
            getAllBooksUseCase
        )

        setContent {
            BiblioTheme {
                val navController = rememberNavController()
                AppNavigation(navController, mainViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController, viewModel) }
        composable("book_list") { BookListScreen(viewModel) }
    }
}