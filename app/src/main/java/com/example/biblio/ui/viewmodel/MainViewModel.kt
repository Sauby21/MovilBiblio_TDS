package com.example.biblio.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biblio.data.repository.DataRepository
import com.example.biblio.domain.Book
import com.example.biblio.domain.Data
import com.example.biblio.domain.GetAllBooksUseCase
import com.example.biblio.domain.ReadDataUseCase
import com.example.domain.DeleteDataUseCase
import com.example.domain.InsertDataUseCase
import com.example.domain.UpdateDataUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val insertDataUseCase: InsertDataUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val deleteDataUseCase: DeleteDataUseCase,
    private val readDataUseCase: ReadDataUseCase,
    private val getAllBooksUseCase: GetAllBooksUseCase,
    private val repository: DataRepository
) : ViewModel() {
    var id = mutableStateOf(0)
    var title = mutableStateOf("")
    var author = mutableStateOf("")
    var stock = mutableStateOf("")
    var isbn = mutableStateOf("")
    var books = mutableStateListOf<Data>()
    val book = mutableStateOf<Book?>(null)
    val message = mutableStateOf("")  // Nuevo estado para mensajes

    // Buscar libro por ISBN y mostrar mensaje si no se encuentra
    fun fetchBookByIsbn(isbnInput: String) {
        viewModelScope.launch {
            val result = repository.fetchBookByIsbn(isbnInput)
            if (result != null) {
                title.value = result.title
                author.value = result.authors.joinToString(", ")
                isbn.value = isbnInput
                message.value = "Libro encontrado"
                book.value = result
                Log.d("MainViewModel", "Book found: ${result.title}")
            } else {
                message.value = "No se encontró ningún libro con ISBN: $isbnInput"
                Log.d("MainViewModel", "No book found with ISBN: $isbnInput")
            }
        }
    }


    // Insertar datos y mostrar mensaje de éxito
    fun insertData() {
        viewModelScope.launch {
            val result = insertDataUseCase(title.value, author.value, stock.value, isbn.value)
            message.value = if (result > 0) "Registro exitoso" else "Error al registrar"
        }
    }

    // Leer datos por ISBN y mostrar en los campos
    fun readData(isbn: String) {
        viewModelScope.launch {
            val data = readDataUseCase(isbn)
            data?.let {
                id.value = it.id
                title.value = it.title
                author.value = it.author
                stock.value = it.stock
                this@MainViewModel.isbn.value = it.isbn
                message.value = "Datos cargados exitosamente"
                Log.d("MainViewModel", "Data found: $it")
            } ?: run {
                message.value = "No se encontraron datos para el ISBN: ${this@MainViewModel.isbn.value}"
            }
        }
    }

    // Actualizar datos y mostrar mensaje de éxito
    fun deleteData(idToDelete: Int) {
        viewModelScope.launch {
            val result = deleteDataUseCase(idToDelete)
            if (result > 0) {
                message.value = "Registro eliminado exitosamente"
                fetchAllBooks() // Recargar la lista de libros
            } else {
                message.value = "Error al eliminar"
            }
        }
    }


    fun updateData() {
        viewModelScope.launch {
            val result = updateDataUseCase(id.value, title.value, author.value, stock.value)
            message.value = if (result > 0) "Cambios realizados exitosamente" else "Error al actualizar"
            Log.d("MainViewModel", "Update result: $result")
        }
    }

    // Obtener todos los libros para BookListScreen
    fun fetchAllBooks() {
        viewModelScope.launch {
            books.clear()
            val allBooks = getAllBooksUseCase()
            books.addAll(allBooks)
            Log.d("MainViewModel", "Fetched books: $allBooks")
        }
    }
    // MainViewModel.kt
    fun loadBookData(bookId: Int) {
        viewModelScope.launch {
            val data = readDataUseCase.invokeById(bookId)
            data?.let {
                id.value = it.id
                title.value = it.title
                author.value = it.author
                stock.value = it.stock
                isbn.value = it.isbn
                message.value = ""  // Limpia el mensaje previo
            } ?: run {
                message.value = "No se encontraron datos para el libro con ID: $bookId"
            }
        }
    }
}

