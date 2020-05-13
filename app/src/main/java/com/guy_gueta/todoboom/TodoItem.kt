package com.guy_gueta.todoboom

class TodoItem(val todoValue : String)
{
    fun createItem() : List<TodoItem>
    {
        val itemList = mutableListOf<TodoItem>()
        return itemList
    }
}