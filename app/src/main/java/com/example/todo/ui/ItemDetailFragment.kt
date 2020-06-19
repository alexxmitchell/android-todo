package com.example.todo.ui

import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentItemDetailBinding
import com.example.todo.utils.DisplayItem
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ItemDetailFragment: Fragment() {
    private val viewModel: ToDoViewModel by sharedViewModel()
    private lateinit var binding: FragmentItemDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_item_detail, container, false)

        binding.todoItemName = String.format(getString(R.string.task_name_formatted), viewModel.selectedToDoItem?.itemName ?: "")
        binding.todoItemStatus = String.format(getString(R.string.completed_formatted), viewModel.selectedToDoItem?.isDone.toString())
        binding.todoItemDate = String.format(getString(R.string.date_created_formatted), DisplayItem.formatDate(viewModel.selectedToDoItem!!.id))
        binding.todoItemCategory = String.format(getString(R.string.category_name_formatted), viewModel.selectedToDoItem?.category ?: "")
        binding.todoItemDeadline = String.format(getString(R.string.deadline_formatted), viewModel.selectedToDoItem?.deadline)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.returnButton.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_item_detail_to_to_do_fragment)
        }

        binding.calendarButton.setOnClickListener {
            addToCalendar()
        }

    }

    private fun addToCalendar(){
        val item = viewModel.selectedToDoItem

        if(item != null){

        val deadlineList = item.deadline.split('-').map {
            it.toInt()
        }
        val startMillis: Long = Calendar.getInstance().run {

            set(deadlineList[0], deadlineList[1], deadlineList[2], 7, 30)
            timeInMillis
        }
        val endMillis: Long = Calendar.getInstance().run {
            set(deadlineList[0], deadlineList[1], deadlineList[2], 8, 30)
            timeInMillis
        }
        val intent = Intent(Intent.ACTION_INSERT)
            .setData(CalendarContract.Events.CONTENT_URI)
            .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
            .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
            .putExtra(CalendarContract.Events.TITLE, item.itemName)
            .putExtra(CalendarContract.Events.DESCRIPTION, item.category)
//            .putExtra(CalendarContract.Events.r, "The gym")
            .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY)
        startActivity(intent)

//            val reminder = Intent(Intent.ACTION_INSERT)
//                .setData(CalendarContract.Reminders.CONTENT_URI)
//                .putExtra(CalendarContract.ACTION_EVENT_REMINDER)
    }
    }
}