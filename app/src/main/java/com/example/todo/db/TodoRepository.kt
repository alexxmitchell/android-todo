package com.example.todo.db

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.todo.ui.ToDoItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


//applicationContext - can see everything inside the app

class TodoRepository(applicationContext: Context) {
    //lateinit must be initialized before being used for database
    private val db: TodoDatabase = Room.databaseBuilder(applicationContext,
        TodoDatabase::class.java, "database-todos").build()

    fun getTodos(): List<ToDoItem>{
        //maps each of the livedata list items
        return db.todoDao().getAll().value?.map { mapRoomTodoToTodoItem(it) } ?: emptyList()
    }

    fun insertTodo(todoItem: ToDoItem){
        //wrapped in coroutine
        GlobalScope.launch {
            val index = db.todoDao().insertOne(mapTodoItemToRoomTodo(todoItem))
            Log.i(index.toString(), "index value")
        }
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