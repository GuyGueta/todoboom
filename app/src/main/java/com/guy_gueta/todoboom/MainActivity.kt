package com.guy_gueta.todoboom

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButton = findViewById<Button>(R.id.button1)
        val myEditText = findViewById<EditText>(R.id.editText1)

        val todoAdapter = TodoItemAdaptor()


        myButton.setOnClickListener {
            val input = myEditText.text
            myEditText.setText("please insert text")
        }

    }
}
