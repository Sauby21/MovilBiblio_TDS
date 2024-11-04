package com.example.domain

import com.example.biblio.data.repository.DataRepository

class UpdateDataUseCase(private val repository: DataRepository) {

    operator fun invoke(id: Int, title: String, author: String, stock: String): Int {
        return repository.updateData(id, title, author, stock)
    }
}
