package com.example.database_task

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.database_task.MyDatabaseHelper
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var dbHelper: MyDatabaseHelper
    lateinit var fab: FloatingActionButton
    lateinit var recycle: RecyclerView
    lateinit var itemlist: ArrayList<data>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = MyDatabaseHelper(this)
        fab = findViewById(R.id.fab)
        recycle = findViewById(R.id.rec)
        itemlist = ArrayList()
        itemlist = dbHelper.readTodo()
        recycle.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycle.adapter = CustomAdapter(itemlist, dbHelper)
        fab.setOnClickListener {
            var index = 1
            var i = Intent(this, Information::class.java)
            i.putExtra("index", index)
            startActivity(i)
        }
    }
}