package com.example.biblio.data.repository

import com.example.biblio.domain.Book
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import com.example.data.model.DatabaseModel
import com.example.biblio.domain.Data
import com.example.biblio.network.DatabookpyApiService

class DataRepository(context: Context) {
    private val dbHelper = DatabaseModel(context)

    // Método para obtener todos los libros
    fun getAllBooks(): List<Data> {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query(
            DatabaseModel.TABLE_NAME,
            arrayOf(DatabaseModel.COL_ID, DatabaseModel.COL_TITLE, DatabaseModel.COL_AUTHOR, DatabaseModel.COL_STOCK, DatabaseModel.COL_ISBN),
            null, null, null, null, null
        )

        val books = mutableListOf<Data>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseModel.COL_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_TITLE))
            val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_AUTHOR))
            val stock = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_STOCK))
            val isbn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_ISBN))
            books.add(Data(id, title, author, stock, isbn))
        }
        cursor.close()
        db.close()
        return books
    }


    // Método para actualizar datos
    fun updateData(id: Int, title: String, author: String, stock: String): Int {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseModel.COL_TITLE, title)
            put(DatabaseModel.COL_AUTHOR, author)
            put(DatabaseModel.COL_STOCK, stock)
        }
        val rowsUpdated = db.update(
            DatabaseModel.TABLE_NAME,
            values,
            "${DatabaseModel.COL_ID}=?",
            arrayOf(id.toString())
        )
        db.close()
        return rowsUpdated
    }


    // Método para eliminar datos
    fun deleteData(id: Int): Int {
        val db = dbHelper.writableDatabase
        val rowsDeleted = db.delete(DatabaseModel.TABLE_NAME, "${DatabaseModel.COL_ID}=?", arrayOf(id.toString()))
        db.close()
        return rowsDeleted
    }

    fun insertData(title: String, author: String, stock: String, isbn: String): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseModel.COL_TITLE, title)
            put(DatabaseModel.COL_AUTHOR, author)
            put(DatabaseModel.COL_STOCK, stock)
            put(DatabaseModel.COL_ISBN, isbn)
        }
        val result = db.insert(DatabaseModel.TABLE_NAME, null, values)
        db.close()
        Log.d("DataRepository", "Insert result: $result")
        return result
    }

    // Método para obtener los datos por ISBN
    fun getDataByIsbn(isbn: String): Data? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseModel.TABLE_NAME,
            arrayOf(DatabaseModel.COL_ID, DatabaseModel.COL_TITLE, DatabaseModel.COL_AUTHOR, DatabaseModel.COL_STOCK, DatabaseModel.COL_ISBN),
            "${DatabaseModel.COL_ISBN} = ?",
            arrayOf(isbn),
            null,
            null,
            null
        )

        var data: Data? = null
        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseModel.COL_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_TITLE))
            val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_AUTHOR))
            val stock = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_STOCK))
            val retrievedIsbn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_ISBN))
            data = Data(id, title, author, stock, retrievedIsbn)
        }

        cursor.close()
        db.close()
        return data
    }
    // DataRepository.kt
    fun getDataById(id: Int): Data? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            DatabaseModel.TABLE_NAME,
            arrayOf(DatabaseModel.COL_ID, DatabaseModel.COL_TITLE, DatabaseModel.COL_AUTHOR, DatabaseModel.COL_STOCK, DatabaseModel.COL_ISBN),
            "${DatabaseModel.COL_ID} = ?",
            arrayOf(id.toString()),
            null, null, null
        )

        var data: Data? = null
        if (cursor.moveToFirst()) {
            val title = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_TITLE))
            val author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_AUTHOR))
            val stock = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_STOCK))
            val isbn = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseModel.COL_ISBN))
            data = Data(id, title, author, stock, isbn)
        }

        cursor.close()
        db.close()
        return data
    }


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://databookpy.onrender.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService = retrofit.create(DatabookpyApiService::class.java)

    suspend fun fetchBookByIsbn(isbn: String): Book? {
        return try {
            Log.d("DataRepository", "Requesting book with ISBN: $isbn")
            val response = apiService.getBookByIsbn(isbn)
            if (response != null) {
                Log.d("DataRepository", "Book found: ${response.title}")
                response
            } else {
                Log.d("DataRepository", "No book found for ISBN: $isbn")
                null
            }
        } catch (e: Exception) {
            Log.e("DataRepository", "Error fetching book by ISBN: $isbn", e)
            null
        }
    }

}
