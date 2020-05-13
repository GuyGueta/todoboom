package com.guy_gueta.todoboom

import android.annotation.SuppressLint
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : Activity() {



    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButton = findViewById<Button>(R.id.button1)
        val myEditText = findViewById<EditText>(R.id.editText1)
        val myTextView = findViewById<TextView>(R.id.textView1)

        myButton.setOnClickListener {
            val input = myEditText.text
            myEditText.setText("please insert text")
            myTextView.text = input
        }

    }
}
