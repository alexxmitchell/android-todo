package com.example.todo

import android.view.View
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day_layout.view.*


//databinding portion of calendar square
class DayViewContainer(view: View) : ViewContainer(view) {

    val textView = view.calendarDayText

    // Without the kotlin android extensions plugin
    // val textView = view.findViewById<TextView>(R.id.calendarDayText)

}