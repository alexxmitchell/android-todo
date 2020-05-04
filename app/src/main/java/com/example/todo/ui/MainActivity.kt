package com.example.todo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.todo.R
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MainActivity : AppCompatActivity() {
//  private val viewModel: ToDoViewModel by sharedViewModel()
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    AndroidThreeTen.init(this)
    setContentView(R.layout.activity_main)


  }

  override fun onStart() {
    super.onStart()

  }

//  fun addItem(item: String) : List {
////takes the list and pushes into it
//  }
}
