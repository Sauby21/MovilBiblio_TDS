package com.example.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.biblio.ui.viewmodel.MainViewModel

@Composable
fun InsertDataScreen(viewModel: MainViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = viewModel.title.value, // Usa `String` en lugar de `TextFieldValue`
            onValueChange = { viewModel.title.value = it },
            label = { Text(text = "Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = viewModel.author.value,
            onValueChange = { viewModel.author.value = it },
            label = { Text(text = "Autor") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = viewModel.stock.value,
            onValueChange = { viewModel.stock.value = it },
            label = { Text(text = "Stock") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = viewModel.isbn.value,
            onValueChange = { viewModel.isbn.value = it },
            label = { Text(text = "ISBN") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.insertData() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Guardar")
        }
    }
}