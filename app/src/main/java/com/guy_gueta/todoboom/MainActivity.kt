package com.guy_gueta.todoboom

import android.annotation.SuppressLint
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val Context.app: MyApp
        get() = applicationContext as MyApp




    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myButton = findViewById<Button>(R.id.button1)
        val myEditText = findViewById<EditText>(R.id.editText1)
        val myAdapter = TodoItemAdaptor()
        initItems(savedInstanceState)
        setAdapter(myAdapter)
        setButton(myButton, myEditText, myAdapter)

    }



    private fun setButton(myButton : Button, myEditText : EditText, myAdapter : TodoItemAdaptor)
    {
        myButton.setOnClickListener {
            val itemsList = app.appManger.itemsList
            val input = myEditText.text
            myEditText.setText("")
            if (input.toString() != "") {
                val item = TodoItem(input.toString(), false)
                itemsList.add(item)
                myAdapter.setAdapter(itemsList)
                app.appManger.saveData()

                Toast.makeText(
                    applicationContext,
                    String.format("ToDo task %s has created. BOOM!", item._todoValue),
                    Toast.LENGTH_SHORT).show()

            }
            else
            {
                Toast.makeText(
                    applicationContext,"you can't create an empty TODO item, oh silly!" ,
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setAdapter(adapter: TodoItemAdaptor)
    {
        val itemsList = app.appManger.itemsList
        adapter.setAdapter(itemsList)
        todo_items_recycler_view.adapter = adapter
        todo_items_recycler_view.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.onTodoItemClickCallback = { todoItem ->
            if (todoItem._Clicked)
            {
                Toast.makeText(this, "TODO ${todoItem._todoValue} is now DONE. BOOM!",
                                Toast.LENGTH_SHORT).show()
                todoItem._Clicked = true
                adapter.setAdapter(itemsList)
                app.appManger.saveData()

            }
            else
            {
                Toast.makeText(this, "TODO ${todoItem._todoValue} is NOT DONE. BOOMER!",
                    Toast.LENGTH_SHORT).show()
                todoItem._Clicked = false
                adapter.setAdapter(itemsList)
                app.appManger.saveData()

            }
        }
        adapter.onTodoItemLongClickCallback = {todoItem ->
            val alertDialog = AlertDialog.Builder(this@MainActivity)
            alertDialog.setTitle("Are You Sure you want to delete this todo item?")
            alertDialog.setPositiveButton("Confirm") { _: DialogInterface, _: Int ->
                itemsList.remove(todoItem)
                adapter.setAdapter(itemsList)
                app.appManger.saveData()
            }
            alertDialog.setNegativeButton("Cancel"){ _: DialogInterface, _: Int -> }
            alertDialog.show()
        }
    }

    private fun initItems(State: Bundle?){
        val itemsList = app.appManger.itemsList
        val inputEditText = findViewById<EditText>(R.id.editText1)
        val tempItems = ArrayList<TodoItem>()
        if (State != null) {
            val userInput = State.getString("userInput")
            inputEditText?.setText(userInput)
            val allContents = State.getStringArray("valuesArray")
            val isClickedArray= State.getBooleanArray("isCheckedArray")
            for (i in allContents!!.indices) {
                if (isClickedArray != null) {
                    tempItems.add(TodoItem(allContents[i], isClickedArray[i]))
                }
            }
            itemsList.clear()
            itemsList.addAll(tempItems)

        }



    }

    override fun onSaveInstanceState(outState: Bundle) {
        val inputText = findViewById<EditText>(R.id.editText1)
        super.onSaveInstanceState(outState)
        val items = app.appManger.itemsList
        val inputVal  =  inputText.text.toString()
        outState.putString("userInput", inputVal)
        val num_of_items  = items.size
        val valuesArray = arrayOfNulls<String>(num_of_items)
        val isCheckedArray = BooleanArray(num_of_items)
        for (i in 0 until num_of_items) {
            valuesArray[i] = items[i]._todoValue
            isCheckedArray[i] = items[i]._Clicked
        }
        outState.putStringArray("valuesArray", valuesArray)
        outState.putBooleanArray("isCheckedArray", isCheckedArray)
    }



}
