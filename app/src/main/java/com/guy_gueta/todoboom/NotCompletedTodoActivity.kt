package com.guy_gueta.todoboom

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class NotCompletedTodoActivity : AppCompatActivity()
{

    private val android.content.Context.app: MyApp
        get() = applicationContext as MyApp

    @SuppressLint("SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_not_completed_todo)
        val id: String? = intent.getStringExtra("id")
        val todo: TodoItem? = id?.let { app.manager.getTodoItem(it) }
        if (todo != null) {
            setView(todo)
            setButtons(todo)
        }
    }


    private fun setButtons(todo: TodoItem)
    {
        findViewById<Button>(R.id.editButton).setOnClickListener {
            val editText: EditText = findViewById<EditText>(R.id.editText1NC)
            val oldContent = todo.content
            val content = editText.text.toString()
            if (content != "") {
                app.manager.updateTodoContent(todo.id, content)
                findViewById<TextView>(R.id.contentFiledNC).text = editText.text.toString()
                Toast.makeText(applicationContext, "TODO $oldContent modified to ${todo.content}.",
                    Toast.LENGTH_SHORT).show()
                editText.text.clear()
            }
            else {
                Toast.makeText(applicationContext
                    , "you can't create an empty TODO item, oh silly!", Toast.LENGTH_SHORT).show()
            }
        }
        findViewById<Button>(R.id.doneButtonNotC).setOnClickListener {
            app.manager.mark(todo.id, true)
            Toast.makeText(applicationContext, "TODO ${todo.content} is now done. BOOM!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun setView(todo: TodoItem)
    {
        findViewById<TextView>(R.id.idFiledNC).text = todo.id
        findViewById<TextView>(R.id.contentFiledNC).text = todo.content
        findViewById<TextView>(R.id.createdFiledNC).text = todo.creation_timestamp
        findViewById<TextView>(R.id.modifyFiledNC).text = todo.edit_timestamp
        findViewById<TextView>(R.id.doneFiledNC).text = if (todo.isDone) {"Yes"} else {"No"}
    }
}
