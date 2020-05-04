package com.example.todo.application

import com.example.todo.db.TodoRepository
import com.example.todo.ui.ToDoViewModel
import io.reactivex.schedulers.Schedulers.single
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // single instance of TodoRepository with context
    single<TodoRepository> { TodoRepository(androidApplication()) }

    // Simple Presenter Factory
    viewModel { ToDoViewModel(todoRepository = get()) }
}
