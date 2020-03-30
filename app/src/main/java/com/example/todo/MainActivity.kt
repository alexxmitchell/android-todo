package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController


class MainActivity : AppCompatActivity() {
  private lateinit var viewModel: ToDoViewModel
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

//    val navController = this.findNavController(R.id.myNavHostFragment)
    setContentView(R.layout.activity_main)


  }

  override fun onStart() {
    super.onStart()
    viewModel = ViewModelProviders.of(this).get(ToDoViewModel::class.java)

  }

//  fun addItem(item: String) : List {
////takes the list and pushes into it
//  }
}
