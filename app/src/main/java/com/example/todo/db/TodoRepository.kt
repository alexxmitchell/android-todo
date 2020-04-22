package com.example.todo.db

import android.content.Context
import androidx.room.Room
import com.example.todo.ui.ToDoItem


//applicationContext - can see everything inside the app

class TodoRepository(applicationContext: Context) {
    //lateinit must be initialized before being used for database
    private val db: TodoDatabase = Room.databaseBuilder(applicationContext,
        TodoDatabase::class.java, "database-todos").build()

    fun getTodos(): List<ToDoItem>{
        //.map -> maps each of the list items
        return db.todoDao().getAll().map { mapRoomTodoToTodoItem(it) }
    }

    fun insertTodo(todoItem: ToDoItem){
        db.todoDao().insertAll(mapTodoItemToRoomTodo(todoItem))
    }

    fun mapRoomTodoToTodoItem(roomTodo: RoomTodo) = ToDoItem(
        itemName = roomTodo.itemName,
        isDone = roomTodo.isDone,
        category = roomTodo.category,
        deadline = roomTodo.deadline,
        addedDate = roomTodo.addedDate
    )
    fun mapTodoItemToRoomTodo(todoItem: ToDoItem) = RoomTodo(
        itemName = todoItem.itemName,
        isDone = todoItem.isDone,
        category = todoItem.category,
        deadline = todoItem.deadline,
        addedDate = todoItem.addedDate,
        id = todoItem.id
    )
}