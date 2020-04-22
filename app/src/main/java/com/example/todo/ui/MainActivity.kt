package com.example.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.todo.R
import com.jakewharton.threetenabp.AndroidThreeTen


class MainActivity : AppCompatActivity() {
  private lateinit var viewModel: ToDoViewModel
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AndroidThreeTen.init(this)
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
