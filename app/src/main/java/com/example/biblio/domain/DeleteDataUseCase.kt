package com.example.domain

import com.example.biblio.data.repository.DataRepository
class DeleteDataUseCase(private val repository: DataRepository) {

    operator fun invoke(id: String): Int {
        return repository.deleteData(id)
    }
}