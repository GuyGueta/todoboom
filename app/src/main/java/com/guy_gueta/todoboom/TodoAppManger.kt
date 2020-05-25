package com.guy_gueta.todoboom

import android.util.Log
import android.annotation.SuppressLint
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TodoAppManger(val context: Context)
{

    val itemsList: ArrayList<TodoItem> = ArrayList()
    private val spItemListKey = "SP_Todo_Items"
    private val currentSizeTag = "the current size of your TODO list is "

    fun saveData(){
        val jasonItemList = Gson().toJson(itemsList)
        val sp = context.getSharedPreferences(spItemListKey, Context.MODE_PRIVATE)
        sp.edit().putString(spItemListKey, jasonItemList).apply()
    }


    @SuppressLint("LongLogTag")
    fun getData(){
        val sp = context.getSharedPreferences(spItemListKey, Context.MODE_PRIVATE)
        val jasonData = sp.getString(spItemListKey, null)
        val type = object : TypeToken<MutableList<TodoItem>>() {}.type
        if (jasonData != null){
            itemsList.addAll(Gson().fromJson(jasonData, type))
            Log.i(currentSizeTag, itemsList.size.toString())
        }
    }




}