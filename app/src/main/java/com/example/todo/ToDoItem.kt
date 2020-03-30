package com.example.todo

data class ToDoItem(val itemName: String, var isDone: Boolean = false) {
    val id = System.currentTimeMillis()


}