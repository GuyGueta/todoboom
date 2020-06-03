package com.guy_gueta.todoboom

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class CompletedTodoActivity : AppCompatActivity()
{
    private val Context.app: MyApp
        get() = applicationContext as MyApp

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_todo)
        val id: String? = intent.getStringExtra("id")
        val todo: TodoItem? = id?.let { app.manager.getTodoItem(it) }
        if (todo != null) {
            setView(todo)
            setButtons(todo, id)
        }
    }


    private fun setButtons(todo: TodoItem, id: String)
    {
        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            val alertDialog = android.app.AlertDialog.Builder(this)
            alertDialog.setTitle("Are You Sure you want to delete this todo task \"${todo.content}\" ")
            alertDialog.setPositiveButton("Confirm") { _: DialogInterface, _: Int ->
                app.manager.deleteTode(todo)
                finish()
                Toast.makeText(this, "TODO ${todo.content} has been deleted.", Toast.LENGTH_SHORT).show()
            }
            alertDialog.setNegativeButton("Cancel"){ _: DialogInterface, _: Int -> }
            alertDialog.show()
        }


        findViewById<Button>(R.id.notCompletedButton).setOnClickListener {
            app.manager.mark(id, false)
            Toast.makeText(this, "TODO ${todo.content} is now notdone. Boomer.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    fun setView(todo: TodoItem)
    {
        findViewById<TextView>(R.id.idFiled).text = todo.id
        findViewById<TextView>(R.id.contentFiled).text = todo.content
        findViewById<TextView>(R.id.creationFiled).text = todo.creation_timestamp
        findViewById<TextView>(R.id.modifyFiled).text = todo.edit_timestamp
        findViewById<TextView>(R.id.doneFiled).text = if (todo.isDone) {"Yes"} else {"No"}
    }
}
