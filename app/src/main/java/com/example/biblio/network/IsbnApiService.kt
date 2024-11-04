package com.example.biblio.network

import com.example.biblio.domain.Book
import retrofit2.http.GET
import retrofit2.http.Query

interface DatabookpyApiService {
    @GET("api/book")
    suspend fun getBookByIsbn(
        @Query("isbn") isbn: String
    ): Book
}

