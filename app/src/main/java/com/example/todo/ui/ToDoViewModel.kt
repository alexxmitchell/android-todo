package com.example.todo.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.todo.db.TodoRepository
//TodoviewModel has the context from todoRepository from the appModule -- being injected
class ToDoViewModel( private val todoRepository : TodoRepository) : ViewModel() {
    private val _todoList = MutableLiveData<List<ToDoItem>>()
    val todoList : LiveData<List<ToDoItem>>
        get() = _todoList
    var selectedToDoItem : ToDoItem? = null
    val todoObserver = Observer<List<ToDoItem>>{
        _todoList.postValue(it)
    }
    init {
       todoRepository.liveDataTodos.observeForever(todoObserver)
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