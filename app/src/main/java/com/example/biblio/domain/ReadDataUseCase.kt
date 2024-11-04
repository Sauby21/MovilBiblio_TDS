package com.example.biblio.domain

import com.example.biblio.data.repository.DataRepository

data class Data(
    val id:Int,
    val title: String,
    val author: String,
    val stock: String,
    val isbn: String
)


// ReadDataUseCase.kt
class ReadDataUseCase(private val repository: DataRepository) {

    suspend operator fun invoke(isbn: String): Data? {
        return repository.getDataByIsbn(isbn)
    }

    suspend fun invokeById(id: Int): Data? {
        return repository.getDataById(id)
    }
}

