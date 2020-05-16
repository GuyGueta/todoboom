package com.guy_gueta.todoboom

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TodoItemAdaptor : RecyclerView.Adapter<TodoItemHolder>() {
    private val _itemsList: ArrayList<TodoItem> = ArrayList()
    var _TodoItemButtonCallback: ToDoItemButtonListener? = null


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
        holder.itemView.setOnClickListener {
            _TodoItemButtonCallback?.onToDoItemClicked(todoItem, this)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemHolder {
        val context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.to_do_item, parent, false)
        return TodoItemHolder(view)
    }
}