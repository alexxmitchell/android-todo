package com.example.todo.ui

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    //ViewHolder is a class inside of ToDoAdapter
    var list: List<ToDoItem> = emptyList()
    var onItemClickDone: ((ToDoItem) -> Unit)? = null
    var onItemClickNavigation: ((ToDoItem) -> Unit)? = null
    //pass in a view/layout of row
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.list_item)
        val checkbox: CheckBox = itemView.findViewById(R.id.list_item_check)
        val deadline: TextView = itemView.findViewById(R.id.list_item_deadline)
        val textContainer: LinearLayout = itemView.findViewById(R.id.textContainer)


        init {
            textContainer.setOnClickListener {
                onItemClickNavigation?.invoke(list[adapterPosition])

            }

            checkbox.setOnClickListener {
                textView.paintFlags = textView.paintFlags xor Paint.STRIKE_THRU_TEXT_FLAG
                onItemClickDone?.invoke(list[adapterPosition])
                //finds the clicked item and gets it

            }


        }
    }


    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val textView = LayoutInflater.from(parent.context).inflate(R.layout.row_item_todo, parent, false)
        return ViewHolder(textView)
    }
    //draws the layout for the specific position in the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position].itemName
        holder.checkbox.isChecked = list[position].isDone
        holder.deadline.text = list[position].deadline



    }
    override fun getItemCount() = list.size

    fun setToDoList(list: List<ToDoItem>) {
        this.list = list
        //redraws the entire list **v bad; calls onCreateViewHolder
        notifyDataSetChanged()
    }





}