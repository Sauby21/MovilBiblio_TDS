package com.example.biblio.domain

data class Book(
    val id: Int,
    val title: String,
    val authors: List<String>,
    val publish_date: String?,
    val isbn: String,
)
