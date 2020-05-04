package com.example.todo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.ui.ToDoItem
//room supports livedata (must be observed, only updates if you remake the getAll())
//can return items as objs, livedata, or flowables
//flowable w/ db - changes everytime the db changes
@Dao
interface RoomTodoDao {
    @Query("SELECT * FROM todos")
    //gets all todos as livedata
    fun getAll(): LiveData<List<RoomTodo>>

    //vararg gives flexibility on items sent
    @Insert
    fun insertAll(vararg todos: RoomTodo)

    @Insert //inserts always return an integer
    fun insertOne(todos: RoomTodo) : Long
}