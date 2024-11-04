package com.example.biblio.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.biblio.domain.Data
import com.example.biblio.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(
    navController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchAllBooks()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Libros") })
        }
    ) { paddingValues ->
        if (viewModel.books.isEmpty()) {
            // Mostrar mensaje si no hay registros
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Sin registros")
            }
        } else {
            // Mostrar la lista de libros si hay registros
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier.padding(16.dp)
            ) {
                items(viewModel.books) { book ->
                    BookItem(book = book, viewModel = viewModel, navController = navController)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}


@Composable
fun BookItem(book: Data, viewModel: MainViewModel, navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "ID: ${book.id}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Título: ${book.title}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Autor: ${book.author}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Stock: ${book.stock}", style = MaterialTheme.typography.bodySmall)
            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.navigate("edit_book/${book.id}")
                    }
                ) {
                    Text("Modificar")
                }
                Button(onClick = {
                    viewModel.deleteData(book.id)
                }) {
                    Text("Eliminar")
                }
            }
        }
    }
}



