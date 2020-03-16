package com.example.todo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    //ViewHolder is a class inside of ToDoAdapter
    var list: List<ToDoItem> = emptyList()
    var onItemClick: ((ToDoItem) -> Unit)? = null
    //pass in a view/layout of row
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: CheckedTextView = itemView.findViewById(R.id.list_item)
        fun updateToDoList() {
            if (!textView.isChecked) {
                textView.isChecked = true
                textView.setCheckMarkDrawable(R.drawable.ic_check_box_checked)
                textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                textView.isChecked = false
                textView.setCheckMarkDrawable(R.drawable.ic_check_box_outline_blank)
                textView.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }
        init {
            textView.setOnClickListener {
                updateToDoList()
                onItemClick?.invoke(list[adapterPosition])
                //finds the clicked item and gets it

            }
        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {
       val textView = LayoutInflater.from(parent.context).inflate(R.layout.row_item_todo, parent, false)
        return ViewHolder(textView)
    }
    //draws the layout for the specific position in the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position].itemName
        if (list[position].isDone) {
            holder.textView.isChecked = true
            holder.textView.setCheckMarkDrawable(R.drawable.ic_check_box_checked)
        } else {
            holder.textView.isChecked = false
            holder.textView.setCheckMarkDrawable(R.drawable.ic_check_box_outline_blank)
        }

    }
    override fun getItemCount() = list.size

    fun setToDoList(list: List<ToDoItem>) {
        this.list = list
        //redraws the entire list **v bad; calls onCreateViewHolder
        notifyDataSetChanged()
    }





}