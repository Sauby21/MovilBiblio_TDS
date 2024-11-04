package com.example.biblio.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.biblio.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = viewModel()) {
    val title by viewModel.title
    val author by viewModel.author
    val stock by viewModel.stock
    val isbn by viewModel.isbn

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // TextFields
        CustomTextField(
            value = title,
            onValueChange = { viewModel.title.value = it },
            label = "Title"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = author,
            onValueChange = { viewModel.author.value = it },
            label = "Author"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = stock,
            onValueChange = { viewModel.stock.value = it },
            label = "Stock"
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomTextField(
            value = isbn,
            onValueChange = { viewModel.isbn.value = it },
            label = "ISBN"
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Buttons for CRUD operations
        CustomButton(
            text = "Insert",
            onClick = { viewModel.insertData() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            text = "Update",
            onClick = { viewModel.updateData() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            text = "Read",
            onClick = { viewModel.readData() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            text = "Delete",
            onClick = { viewModel.deleteData() }
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomButton(
            text = "View Book List",
            onClick = { navController.navigate("book_list") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        // New button to navigate to BookInfoScreen
        CustomButton(
            text = "Search Book by ISBN",
            onClick = { navController.navigate("bookInfo") }
        )
    }
}
@Composable
fun CustomTextField(value: String, onValueChange: (String) -> Unit, label: String) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

@Composable
fun CustomButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(50.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(text)
    }
}
