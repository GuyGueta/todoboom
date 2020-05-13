package com.guy_gueta.todoboom

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItemHolder(view : View) : RecyclerView.ViewHolder(view){
    val todoItemText: TextView = view.findViewById(R.id.to_do_item)
}