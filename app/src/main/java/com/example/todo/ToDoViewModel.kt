package com.example.todo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ToDoViewModel : ViewModel() {
    private val _todoList = MutableLiveData<List<String>>()
    val todoList : LiveData<List<String>>
        get() = _todoList
    init {
        _todoList.postValue(mutableListOf())
    }

    fun addTodo(str: String){
        val list = _todoList.value?.toMutableList() ?: mutableListOf()
        list.add(str)
        Log.i("addToDo", "str")
        _todoList.postValue(list)
        Log.i("addToDo", _todoList.value.toString())
    }

    fun removeTodo(str: String) {
        if (_todoList.value != null) {
            val list = _todoList.value!!.toMutableList()
            list.remove(str)
            _todoList.postValue(list)
        }

    }
}