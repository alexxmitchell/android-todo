package com.example.todo.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.ui.ToDoItem

@Dao
interface RoomTodoDao {
    @Query("SELECT * FROM todos")
    fun getAll(): List<RoomTodo>

    //vararg gives flexibility on items sent
    @Insert
    fun insertAll(vararg todos: RoomTodo)
}