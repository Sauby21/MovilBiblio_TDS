package com.example.domain

import com.example.biblio.data.repository.DataRepository

class InsertDataUseCase(private val repository: DataRepository) {
    operator fun invoke(title: String, author: String, stock: String, isbn: String): Long {
        return repository.insertData(title, author, stock, isbn)
    }
}