package com.guy_gueta.todoboom

class TodoItem(val item : String)
{
    fun createTodoItem() : List<TodoItem>
    {
        val itemList = mutableListOf<TodoItem>()
        return itemList
    }
}