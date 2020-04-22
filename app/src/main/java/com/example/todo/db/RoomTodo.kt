package com.example.todo.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
 data class RoomTodo (
    @PrimaryKey val id: Long,
    @ColumnInfo( name = "item_name") val itemName: String,
    @ColumnInfo( name = "is_done") val isDone: Boolean,
    @ColumnInfo( name = "category") val category: String,
    @ColumnInfo( name = "deadline") val deadline: String,
    @ColumnInfo( name = "added_date") val addedDate: Long
    )