package com.guy_gueta.todoboom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoItemAdaptor : RecyclerView.Adapter<TodoItemHolder>() {
    private val _itemsList: ArrayList<TodoItem> = ArrayList()
    var onTodoItemClickCallback: ((TodoItem) -> Unit)? = null


    fun setAdapter(items : ArrayList<TodoItem>)
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
        holder.todoItemText.text = todoItem._todoValue
        holder.checkBox.isChecked = todoItem._Clicked
    }

    private fun onTodoItemClicked(holder: TodoItemHolder)
    {
        holder.itemView.setOnClickListener {
            val item = _itemsList[holder.adapterPosition]
            val callback = onTodoItemClickCallback ?: return@setOnClickListener
            if (!item._Clicked)
            {
                holder.checkBox.isChecked = true
                item._Clicked = true
            }
            else
            {
                holder.checkBox.isChecked = false
                item._Clicked = false
                setAdapter(_itemsList)
            }
            callback(item)

    }
}


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.to_do_item, parent, false)
        val holder = TodoItemHolder(view)
        onTodoItemClicked(holder)
        return holder
    }
}