package com.example.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.biblio.ui.viewmodel.MainViewModel

@Composable
fun InsertDataScreen(viewModel: MainViewModel = viewModel()) {
    var isbnInput by remember { mutableStateOf("") }
    val title by viewModel.title
    val author by viewModel.author
    val stock by viewModel.stock
    val message by viewModel.message  // Para mostrar mensajes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de ISBN
        TextField(
            value = isbnInput,
            onValueChange = { isbnInput = it },
            label = { Text(text = "ISBN") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Botón para buscar libro por ISBN
        Button(
            onClick = {
                viewModel.fetchBookByIsbn(isbnInput) // Llama a la función para buscar el libro
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de título (rellenado después de la búsqueda)
        TextField(
            value = title,
            onValueChange = { viewModel.title.value = it },
            label = { Text(text = "Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de autor (rellenado después de la búsqueda)
        TextField(
            value = author,
            onValueChange = { viewModel.author.value = it },
            label = { Text(text = "Autor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Campo de stock (rellenado manualmente)
        TextField(
            value = stock,
            onValueChange = { viewModel.stock.value = it },
            label = { Text(text = "Stock") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón para guardar datos en la base de datos
        Button(
            onClick = { viewModel.insertData() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Guardar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar mensaje de éxito o error
        if (message.isNotBlank()) {
            Text(text = message, color = MaterialTheme.colorScheme.primary)
        }
    }
}
