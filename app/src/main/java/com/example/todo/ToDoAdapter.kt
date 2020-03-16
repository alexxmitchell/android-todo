package com.example.todo

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CheckedTextView
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ViewHolder>() {
    //ViewHolder is a class inside of ToDoAdapter
    var list: List<ToDoItem> = emptyList()
    var onItemClickDone: ((ToDoItem) -> Unit)? = null
    var onItemClickNavigation: ((ToDoItem) -> Unit)? = null
    //pass in a view/layout of row
    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.list_item)
        val checkbox: CheckBox = itemView.findViewById(R.id.list_item_check)

        init {
            textView.setOnClickListener {
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoAdapter.ViewHolder {
       val textView = LayoutInflater.from(parent.context).inflate(R.layout.row_item_todo, parent, false)
        return ViewHolder(textView)
    }
    //draws the layout for the specific position in the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = list[position].itemName
        holder.checkbox.isChecked = list[position].isDone
//


    }
    override fun getItemCount() = list.size

    fun setToDoList(list: List<ToDoItem>) {
        this.list = list
        //redraws the entire list **v bad; calls onCreateViewHolder
        notifyDataSetChanged()
    }





}