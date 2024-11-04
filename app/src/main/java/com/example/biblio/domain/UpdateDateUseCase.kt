package com.example.domain
import com.example.biblio.data.repository.DataRepository

class UpdateDataUseCase(private val repository: DataRepository) {

    operator fun invoke(isbn: String, title: String, author: String, stock: String): Int {
        return repository.updateData(isbn, title, author, stock)
    }
}
