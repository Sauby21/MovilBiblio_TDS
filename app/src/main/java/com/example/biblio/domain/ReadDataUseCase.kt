package com.example.biblio.domain

import com.example.biblio.data.repository.DataRepository

data class Data(
    val title: String,
    val author: String,
    val stock: String,
    val isbn: String
)


class ReadDataUseCase(private val repository: DataRepository) {

    // El método "invoke" permite que el caso de uso se llame directamente como una función
    suspend operator fun invoke(isbn: String): Data? {
        // Llama al repositorio para obtener los datos por ISBN
        return repository.getDataByIsbn(isbn)
    }
}
