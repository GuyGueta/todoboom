package com.guy_gueta.todoboom

class ToDoItemButtonListener(val list: ArrayList<TodoItem>) {

    fun onToDoItemClicked(item: TodoItem, adapter : TodoItemAdaptor)
    {
        if (!item._Clicked) {
            item._Clicked = true
            adapter.setAdapter(list)
        }
        else
        {
            item._Clicked = false
            adapter.setAdapter(list)
        }
    }
}