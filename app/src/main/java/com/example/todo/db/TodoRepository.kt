package com.example.todo.db

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.todo.ui.ToDoItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


//applicationContext - can see everything inside the app

class TodoRepository(private val db: TodoDatabase) {
    //db is being injected by Koin
    val liveDataAllRoomTodos: LiveData<List<RoomTodo>> = db.todoDao().getAll()
    val liveDataTodos = MutableLiveData<List<ToDoItem>>()
    val todoObserver = Observer<List<RoomTodo>>{
        val todoList = it.map { mapRoomTodoToTodoItem(it) } //creates a list of roomtodoitems
        liveDataTodos.postValue(todoList)
    }
    init {
        liveDataAllRoomTodos.observeForever(todoObserver)
    }
    fun getTodos(): List<ToDoItem>{
        //maps each of the livedata list items
        return db.todoDao().getAll().value?.map { mapRoomTodoToTodoItem(it) } ?: emptyList()
    }

    fun insertTodo(todoItem: ToDoItem){
        //wrapped in coroutine
        GlobalScope.launch {
            val index = db.todoDao().insertOne(mapTodoItemToRoomTodo(todoItem))
        }
    }

    // only visible for testing, any other time, it will be private
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun mapRoomTodoToTodoItem(roomTodo: RoomTodo) = ToDoItem(
        itemName = roomTodo.itemName,
        isDone = roomTodo.isDone,
        category = roomTodo.category,
        deadline = roomTodo.deadline,
        addedDate = roomTodo.addedDate
    )

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    fun mapTodoItemToRoomTodo(todoItem: ToDoItem) = RoomTodo(
        itemName = todoItem.itemName,
        isDone = todoItem.isDone,
        category = todoItem.category,
        deadline = todoItem.deadline,
        addedDate = todoItem.addedDate,
        id = todoItem.id
    )
}