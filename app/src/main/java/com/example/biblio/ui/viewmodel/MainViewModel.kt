package com.example.biblio.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.biblio.domain.ReadDataUseCase
import com.example.domain.DeleteDataUseCase
import com.example.domain.InsertDataUseCase
import com.example.domain.UpdateDataUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val insertDataUseCase: InsertDataUseCase,
    private val updateDataUseCase: UpdateDataUseCase,
    private val deleteDataUseCase: DeleteDataUseCase,
    private val readDataUseCase: ReadDataUseCase
) : ViewModel() {
    var title = mutableStateOf("")
    var author = mutableStateOf("")
    var stock = mutableStateOf("")
    var isbn = mutableStateOf("")

    fun insertData() {
        viewModelScope.launch {
            val result = insertDataUseCase(title.value, author.value, stock.value, isbn.value)
            Log.d("MainViewModel", "Insert result: $result")
        }
    }

    fun deleteData() {
        viewModelScope.launch {
            val result = deleteDataUseCase(isbn.value)
            Log.d("MainViewModel", "Delete result: $result")
        }
    }

    fun readData() {
        viewModelScope.launch {
            val data = readDataUseCase(isbn.value)
            data?.let {
                title.value = it.title
                author.value = it.author
                stock.value = it.stock
                isbn.value = it.isbn
                Log.d("MainViewModel", "Data found: $it")
            } ?: Log.d("MainViewModel", "No data found for ISBN: ${isbn.value}")
        }
    }

    fun updateData() {
        viewModelScope.launch {
            val result = updateDataUseCase(
                isbn.value,
                title.value,
                author.value,
                stock.value
            )
            Log.d("MainViewModel", "Update result: $result")
        }
    }
}
