package com.example.biblio.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.biblio.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Botón para ir a InsertDataScreen
        Button(
            onClick = { navController.navigate("insert_data") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Ir a Insertar Datos")
        }

        // Botón para ir a BookListScreen
        Button(
            onClick = { navController.navigate("book_list") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Ver Lista de Libros")
        }

        // Botón para ir a BookInfoScreen
        Button(
            onClick = { navController.navigate("bookInfo") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Text("Buscar Libro por ISBN")
        }
    }
}
