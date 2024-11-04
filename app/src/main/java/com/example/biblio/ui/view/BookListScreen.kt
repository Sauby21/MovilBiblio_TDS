// BookListScreen.kt

package com.example.biblio.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.biblio.domain.Data
import com.example.biblio.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookListScreen(viewModel: MainViewModel = viewModel()) {
    LaunchedEffect(Unit) {
        viewModel.fetchAllBooks()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Lista de Libros") })
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier.padding(16.dp)
        ) {
            items(viewModel.books) { book ->
                BookItem(book)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun BookItem(book: Data) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Título: ${book.title}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Autor: ${book.author}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Stock: ${book.stock}", style = MaterialTheme.typography.bodySmall)
            Text(text = "ISBN: ${book.isbn}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
