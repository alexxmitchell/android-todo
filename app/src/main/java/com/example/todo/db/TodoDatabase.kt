package com.example.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomTodo::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : RoomTodoDao
}