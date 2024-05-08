package com.example.database_task


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.view.isVisible

class Information : AppCompatActivity() {
    lateinit var back: Button
    lateinit var dbHelper: MyDatabaseHelper
    lateinit var ID: EditText
    lateinit var Title: EditText
    lateinit var ButtonGroup: RadioGroup
    lateinit var RadioDone: RadioButton
    lateinit var RadioNotDone: RadioButton
    lateinit var Body: EditText
    lateinit var Add: Button
    lateinit var Update: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        back = findViewById(R.id.back)
        dbHelper = MyDatabaseHelper(this)
        Add = findViewById(R.id.add)
        Update = findViewById(R.id.update)
        ID = findViewById(R.id.id)
        Title = findViewById(R.id.title)
        Body = findViewById(R.id.body)
        ButtonGroup = findViewById(R.id.group)
        RadioDone = findViewById(R.id.done)
        RadioNotDone = findViewById(R.id.notdone)
        val ID_result = intent.getIntExtra("ID", 0)
        val Title_result = intent.getStringExtra("Title")
        val Body_result = intent.getStringExtra("Body")
        val isDone_result = intent.getBooleanExtra("isDone", true)
        ID.setText("ID : " + ID_result.toString())
        Title.setText(Title_result)
        Body.setText(Body_result)
        if (isDone_result) {
            RadioDone.isChecked = true
        } else {
            RadioNotDone.isChecked = true
        }
        val index = intent.getIntExtra("index", 0)
        if (index == 1) {
            Update.visibility = View.GONE
            ID.visibility = View.GONE
        } else if (index == 0) {
            Update.isVisible = true
            Add.visibility = View.GONE
            ID.visibility = View.GONE
        }
        back.setOnClickListener {
            var i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        Add.setOnClickListener {
            var id = 0
            var isDone = if (RadioDone.isChecked) true else false
            dbHelper.insertTodo(data(id, Title.text.toString(), Body.text.toString(), isDone))
            Toast.makeText(this, "The operation succeed", Toast.LENGTH_LONG).show()
        }
        Update.setOnClickListener {
            var isDone = if (RadioDone.isChecked) true else false
            dbHelper.updateTodo(
                data(
                    ID_result,
                    Title.text.toString(),
                    Body.text.toString(),
                    isDone
                )
            )
            Toast.makeText(this, "The operation succeed", Toast.LENGTH_LONG).show()
        }
    }
}