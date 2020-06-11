package com.example.todo.ui

import java.util.*

data class ToDoItem(val itemName: String,
                    var isDone: Boolean = false,
                    val category: String,
                    val deadline: String,
                    val addedDate: Long = System.currentTimeMillis()) {
                    val id = addedDate


}