package com.example.todo

import java.util.*

data class ToDoItem(val itemName: String, var isDone: Boolean = false, val category: String, val deadline: String) {
    val id = System.currentTimeMillis()


}