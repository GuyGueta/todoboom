package com.guy_gueta.todoboom



data class TodoItem(var content: String, var isDone: Boolean, val creation_timestamp: String,
                    var edit_timestamp: String, var id: String = "") {
    constructor(): this("", false, "", "", "")
}

