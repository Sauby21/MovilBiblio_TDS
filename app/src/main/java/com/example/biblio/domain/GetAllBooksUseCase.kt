// GetAllBooksUseCase.kt

package com.example.biblio.domain

import com.example.biblio.data.repository.DataRepository

class GetAllBooksUseCase(private val repository: DataRepository) {

    operator fun invoke(): List<Data> {
        return repository.getAllBooks()
    }
}
