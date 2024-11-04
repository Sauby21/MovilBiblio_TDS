package com.example.biblio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.biblio.data.repository.DataRepository
import com.example.biblio.domain.ReadDataUseCase
import com.example.biblio.ui.theme.BiblioTheme
import com.example.biblio.ui.view.MainScreen
import com.example.biblio.ui.viewmodel.MainViewModel
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

        val mainViewModel = MainViewModel(
            insertDataUseCase,
            updateDataUseCase,
            deleteDataUseCase,
            readDataUseCase
        )

        setContent {
            BiblioTheme {
                MainScreen(viewModel = mainViewModel)
            }
        }
    }
}

