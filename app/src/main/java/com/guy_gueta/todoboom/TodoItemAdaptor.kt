package com.guy_gueta.todoboom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoItemAdaptor : RecyclerView.Adapter<TodoItemHolder>() {
    private val _itemsList: MutableList<TodoItem> = ArrayList()


    fun setItem(items : ArrayList<TodoItem>)
    {
        _itemsList.clear()
        _itemsList.addAll(items)
        notifyDataSetChanged()

    }


    override fun getItemCount(): Int
    {
        return _itemsList.size
    }

    override fun onBindViewHolder(holder: TodoItemHolder, position: Int) {
        val todoItem = _itemsList[position]
        holder.todoItemText.text = todoItem.todoValue

        holder.itemView.setOnClickListener {

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.to_do_item, parent, false)
        return TodoItemHolder(view)
    }
}