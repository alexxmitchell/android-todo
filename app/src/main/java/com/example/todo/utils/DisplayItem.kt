package com.example.todo.utils

import java.text.SimpleDateFormat
import java.util.*


class DisplayItem {
    companion object{
        fun formatDate(id: Long) : String {
            // Create a DateFormatter object for displaying date in specified format.
            // Create a DateFormatter object for displaying date in specified format.
            val formatter = SimpleDateFormat("MM/dd/yyyy")

            // Create a calendar object that will convert the date and time value in milliseconds to date.
            // Create a calendar object that will convert the date and time value in milliseconds to date.
            val calendar: Calendar = Calendar.getInstance()
            calendar.timeInMillis = id
            return formatter.format(calendar.time)

        }

    }
}