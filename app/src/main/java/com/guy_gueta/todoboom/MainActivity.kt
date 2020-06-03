package com.guy_gueta.todoboom

import android.annotation.SuppressLint
import android.content.*
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private val Context.app: MyApp
        get() = applicationContext as MyApp

    private val myAdapter = TodoItemAdaptor()


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val displayButton = findViewById<Button>(R.id.button1)
        val editText = findViewById<EditText>(R.id.editText1)
        initItems(savedInstanceState)
        setupAdapter()
        displayButton.setOnClickListener {
            if (editText.text != null && editText.text.toString() != "")
            {
                val timeS = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().time)
                val todoItem = TodoItem(editText.text.toString(), false, timeS, timeS)
                app.manager.addTodo(todoItem)
                myAdapter.setAdapter(app.manager.getTodoList())

            }
            else
            {
                Toast.makeText(this, "you can't create an empty TODO item, oh silly!.", Toast.LENGTH_SHORT).show()
            }
            editText.text.clear()

        }
        val broadcastReceiver = (object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent?) {
                myAdapter.setAdapter(app.manager.getTodoList())
            }
        })
        LocalBroadcastManager.getInstance(applicationContext)
            .registerReceiver(broadcastReceiver, IntentFilter("Intent Action"))

    }


    private fun setupAdapter() {
        myAdapter.setAdapter(app.manager.getTodoList())
        todo_items_recycler_view.adapter = myAdapter
        todo_items_recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        myAdapter.onTodoItemClickCallback = { todo ->
            val intent: Intent?
            intent = if (todo.isDone) {
                Intent(this@MainActivity, CompletedTodoActivity::class.java)
            } else {
                Intent(this@MainActivity, NotCompletedTodoActivity::class.java)
            }
            intent.putExtra("id", todo.id)
            startActivity(intent)
            myAdapter.setAdapter(app.manager.getTodoList())
        }
    }


    private fun initItems(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            findViewById<EditText>(R.id.editText1).setText(savedInstanceState.getString("input"))
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        val input = findViewById<EditText>(R.id.editText1).toString()
        super.onSaveInstanceState(outState)
        outState.putString("input", input)
    }

    override fun onResume() {
        super.onResume()
        myAdapter.setAdapter(app.manager.getTodoList())
    }



}
