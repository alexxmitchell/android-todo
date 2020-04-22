package com.example.todo.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.db.TodoRepository

class ToDoViewModel(applicationContext: Application) : AndroidViewModel(applicationContext) {
    private val todoRepository : TodoRepository = TodoRepository(applicationContext)
    private val _todoList = MutableLiveData<List<ToDoItem>>()
    val todoList : LiveData<List<ToDoItem>>
        get() = _todoList
    var selectedToDoItem : ToDoItem? = null
    init {
        _todoList.postValue(todoRepository.getTodos().toMutableList()?: mutableListOf())
    }

    fun addTodo(todo: ToDoItem) {
        todoRepository.insertTodo(todo)
        val list = _todoList.value?.toMutableList() ?: mutableListOf()
        list.add(todo)
        Log.i("addToDo", "str")
        _todoList.postValue(list)
        Log.i("addToDo", _todoList.value.toString())
    }

    fun updateTodo( id: Long) {
        val list = _todoList.value?.toMutableList() ?: mutableListOf()
        val item = list.find { id == it.id }
        item?.isDone = item?.let { !item.isDone }?: false
    }

    fun setSelected(todo: ToDoItem) {
        selectedToDoItem = todo
    }


//    fun removeTodo(str: String) {
//        if (_todoList.value != null) {
//            val list = _todoList.value!!.toMutableList()
//            list.remove(str)
//            _todoList.postValue(list)
//        }
//
//    }
}