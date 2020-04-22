package com.example.todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.databinding.FragmentNewItemBinding
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.ScrollMode
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import kotlinx.android.synthetic.main.calendar_day_layout.view.*
import kotlinx.android.synthetic.main.fragment_new_item.*
import org.threeten.bp.LocalDate
import org.threeten.bp.YearMonth
import org.threeten.bp.temporal.WeekFields
import java.util.*

class NewItemFragment : Fragment() {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var binding: FragmentNewItemBinding
    private var selectedDate: LocalDate? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!).get(ToDoViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_new_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //databinding portion of calendar square
        class DayViewContainer(view: View) : ViewContainer(view) {


            lateinit var day: CalendarDay // Will be set when this container is bound.
            val textView : TextView = view.calendarDayText

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDate != day.date) {
                            val oldDate = selectedDate
                            selectedDate = day.date
                            calendarView.notifyDateChanged(day.date)
                            oldDate?.let { calendarView.notifyDateChanged(it) }

                        }
                    }
                }
            }


        }
        calendarView.dayBinder = object : DayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                container.textView.text = day.date.dayOfMonth.toString()
            }
        }

        val currentMonth = YearMonth.now()
        val lastMonth = currentMonth.plusMonths(10)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek
        calendarView.setup(currentMonth, lastMonth, firstDayOfWeek)
        calendarView.scrollToMonth(currentMonth)
        calendarView.scrollMode = ScrollMode.PAGED
        calendarView.orientation = RecyclerView.HORIZONTAL

        add.setOnClickListener {
            addNewItem()
            findNavController().navigate(R.id.action_fragment_new_item_to_to_do_fragment)
        }
    }

    private fun addNewItem() {
        val itemName = edit_task_name.text
        val itemCategory = edit_category_name.text
        val itemDeadline = selectedDate.toString()
        val toDoItem = ToDoItem(
            itemName.toString(),
            false,
            itemCategory.toString(),
            itemDeadline
        )
        viewModel.addTodo(toDoItem)
    }
}