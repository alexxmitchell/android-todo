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
import com.example.todo.databinding.ToDoFragmentBinding
import kotlinx.android.synthetic.main.to_do_fragment.*


class ToDoFragment : Fragment() {
    private lateinit var viewModel: ToDoViewModel
    private lateinit var binding: ToDoFragmentBinding


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

//        binding.todoList = viewModel.todoList?.value
        viewModel.todoList.observe(this, Observer { todos ->
            val stringBuilder = StringBuilder()
            todos.forEach { item -> stringBuilder.append(item).append("\n") }

            list_item.text = stringBuilder.toString()
        })


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_todo.setOnClickListener {
            val text = edit_field.text.toString()
            viewModel.addTodo(text)
            Log.i("Fragment", "added a todo")
            edit_field.setText("")
        }
    }
}