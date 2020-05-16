package com.guy_gueta.todoboom

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItemHolder(view : View) : RecyclerView.ViewHolder(view){
    val todoItemText: TextView = view.findViewById(R.id.todo_item_text_View)
    val checkBox: CheckBox = view.findViewById(R.id.todo_item_check_box)
}