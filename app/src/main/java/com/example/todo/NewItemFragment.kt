package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.FragmentNewItemBinding
import kotlinx.android.synthetic.main.fragment_new_item.*

class NewItemFragment : Fragment() {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var binding: FragmentNewItemBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!).get(ToDoViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_item, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add.setOnClickListener {
            addNewItem()
            findNavController().navigate(R.id.action_fragment_new_item_to_to_do_fragment)
        }
    }

    private fun addNewItem() {
        val itemName = edit_task_name.text
        val itemCategory = edit_category_name.text
        val toDoItem = ToDoItem(itemName.toString(), false, itemCategory.toString())
        viewModel.addTodo(toDoItem)
    }
}