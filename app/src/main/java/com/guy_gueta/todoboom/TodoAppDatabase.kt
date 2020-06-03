package com.guy_gueta.todoboom

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class TodoAppDatabase(val context: Context)
{
    private val todoList: ArrayList<TodoItem> = ArrayList()
    private val firestore = FirebaseFirestore.getInstance()

    init {
        val firestore = FirebaseFirestore.getInstance()
        val referenceToCollection = firestore.collection("todoList")

        referenceToCollection.addSnapshotListener { value, exception ->
            if (exception != null) {
                return@addSnapshotListener
            }
            if (value == null) {
                return@addSnapshotListener
            }
            this.todoList.clear()
            for (document: QueryDocumentSnapshot in value) {
                val item = document.toObject(TodoItem::class.java)
                this.todoList.add(item)
            }
        }
        referenceToCollection.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val intent = Intent("Intent Action")
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
            }
        }
    }


    fun addTodo(todo: TodoItem) {
        todoList.add(todo)
        val doc = firestore.collection("todoList").document()
        todo.id = doc.id
        doc.set(todo)
    }

    fun deleteTode(todo: TodoItem) {
        todoList.remove(todo)
        val docId = todo.id
        firestore.collection("todoList").document(docId).delete()
    }

    @SuppressLint("SimpleDateFormat")
    fun mark(id: String, done: Boolean) {
        val todo: TodoItem? = todoList.find { it!!.id == id }
        todo?.isDone = done
        todo?.edit_timestamp = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().time)
        if (todo != null) {
            firestore.collection("todoList").document(id).set(todo)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun updateTodoContent(id: String, content: String)
    {
        val todo: TodoItem? = todoList.find { it!!.id == id }
        todo?.content = content
        todo?.edit_timestamp = SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(Calendar.getInstance().time)
        if (todo != null) {
            firestore.collection("todoList").document(id).set(todo)
        }
    }

    fun getTodoItem(id: String): TodoItem? {
        return todoList.find { it!!.id == id }
    }


    fun getTodoList(): ArrayList<TodoItem> {
        return todoList
    }
}


