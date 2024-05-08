package com.example.database_task

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class MyDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "my_database.db", null, 1) {

    val TABLE_NAME = "TODO"
    val COLUMN_ID = "Id"
    val COLUMN_Title = "Title"
    val COLUMN_Body = "Body"
    val COLUMN_isDone = "isDone"

    override fun onCreate(db: SQLiteDatabase) {
        db?.execSQL(
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_Title TEXT NOT NULL," +
                    "$COLUMN_Body TEXT NOT NULL," +
                    "$COLUMN_isDone Boolean NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertTodo(data: data) {
        val db = this.writableDatabase        // insert  update delete
        var contentvalues = ContentValues().apply {
            put(COLUMN_Title, data.Title)
            put(COLUMN_Body, data.Body)
            put(COLUMN_isDone, data.isDone)

        }
        db.insert(TABLE_NAME, null, contentvalues)
        db.close()
    }

    fun updateTodo(data: data) {
        val db = this.writableDatabase
        var contentvalues = ContentValues().apply {
            put(COLUMN_Title, data.Title)
            put(COLUMN_Body, data.Body)
            put(COLUMN_isDone, data.isDone)
        }
        db.update(TABLE_NAME, contentvalues, "$COLUMN_ID = ? ", arrayOf(data.ID.toString()))
        db.close()
    }

    fun deleteTodo(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun readTodo(): ArrayList<data> {
        val db = this.readableDatabase
        var cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        var TodoArrayList = ArrayList<data>()
        if (cursor.moveToFirst()) {
            do {
                var ID = cursor.getInt(0)
                var Title = cursor.getString(1)
                var Body = cursor.getString(2)
                var isDone = cursor.getInt(3).equals(1)
                var data = data(ID, Title, Body, isDone)
                TodoArrayList.add(data)
            } while (cursor.moveToNext())
        }
        db.close()
        return TodoArrayList
    }

}