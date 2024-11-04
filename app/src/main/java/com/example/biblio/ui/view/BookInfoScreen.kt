// BookInfoScreen.kt
package com.example.biblio.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.biblio.ui.viewmodel.MainViewModel

@Composable
fun BookInfoScreen(viewModel: MainViewModel = viewModel()) {
    var isbn by remember { mutableStateOf("") }
    val book by viewModel.book
    val message by viewModel.message

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = isbn,
            onValueChange = { isbn = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.fetchBookByIsbn(isbn)
        }) {
            Text("Buscar libro")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Mostrar detalles del libro si existen
        book?.let {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Título
                    Text(
                        text = it.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Divider(modifier = Modifier.padding(vertical = 8.dp))

                    // Autor
                    BookDetailRow(
                        label = "Autor(es):",
                        detail = it.authors.joinToString(", ")
                    )

                    // Fecha de publicación
                    BookDetailRow(
                        label = "Fecha de publicación:",
                        detail = it.publish_date ?: "No disponible"
                    )
                }
            }
        } ?: Text(
            text = "Introduce un ISBN válido y presiona 'Buscar libro'.",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun BookDetailRow(label: String, detail: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = detail,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

