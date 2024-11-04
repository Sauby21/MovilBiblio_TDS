package com.example.data.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseModel(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "MyDatabase"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "MyTable"
        const val COL_ID = "_id"
        const val COL_TITLE = "title"
        const val COL_AUTHOR = "author"
        const val COL_STOCK = "stock"
        const val COL_ISBN = "isbn"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
           CREATE TABLE IF NOT EXISTS $TABLE_NAME(
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COL_TITLE TEXT NOT NULL,
            $COL_AUTHOR TEXT NOT NULL,
            $COL_STOCK TEXT NOT NULL,
            $COL_ISBN TEXT NOT NULL
           );
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}