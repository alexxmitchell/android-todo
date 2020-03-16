package com.example.todo


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.ToDoFragmentBinding
import kotlinx.android.synthetic.main.to_do_fragment.*
import kotlinx.android.synthetic.main.to_do_fragment.view.*


class ToDoFragment : Fragment() {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var binding: ToDoFragmentBinding
    private lateinit var todoListAdapter: ToDoAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("ViewModel", "Called ViewModelProviders.of")
        viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.to_do_fragment,
            container,
            false
        )

        todoListAdapter= ToDoAdapter()
//        binding.todoList = viewModel.todoList?.value
        viewModel.todoList.observe(this, Observer { todos ->
            todoListAdapter.setToDoList(todos)
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //connects RecyclerView to fragment

        todo_list.layoutManager = LinearLayoutManager(context)
        todo_list.adapter = todoListAdapter
        todoListAdapter.onItemClickDone = {
            viewModel.updateTodo(it.id)
        }
        todoListAdapter.onItemClickNavigation = {
            findNavController().navigate(R.id.action_to_do_fragment_to_fragment_item_detail)
        }
        add_todo.setOnClickListener {
            val text = edit_field.text.toString()
            viewModel.addTodo(text)
            Log.i("Fragment", "added a todo")
            edit_field.setText("")
        }
    }
}