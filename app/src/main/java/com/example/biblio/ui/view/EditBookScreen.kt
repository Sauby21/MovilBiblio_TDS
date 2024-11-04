package com.example.biblio.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.biblio.ui.viewmodel.MainViewModel

@Composable
fun EditBookScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
    bookId: Int
) {
    // Llama a la función para cargar los datos del libro al entrar a la pantalla
    LaunchedEffect(bookId) {
        viewModel.loadBookData(bookId) // Llama a loadBookData solo una vez
    }

    val title by viewModel.title
    val author by viewModel.author
    val stock by viewModel.stock
    val isbn by viewModel.isbn
    val message by viewModel.message

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = title,
            onValueChange = { viewModel.title.value = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = author,
            onValueChange = { viewModel.author.value = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = stock,
            onValueChange = { viewModel.stock.value = it },
            label = { Text("Stock") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = isbn,
            onValueChange = { viewModel.isbn.value = it },
            label = { Text("ISBN") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.updateData()  // Llama a la función de actualización
            viewModel.message.value = "Datos modificados exitosamente"  // Muestra mensaje de éxito
        }) {
            Text("Guardar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (message.isNotBlank()) {
            Text(text = message, color = MaterialTheme.colorScheme.primary)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            navController.navigate("main") // Navega al menú principal
        }) {
            Text("Ir al Menú")
        }
    }
}
