package com.example.todo.application

import androidx.room.Room
import com.example.todo.db.TodoDatabase
import com.example.todo.db.TodoRepository
import com.example.todo.ui.ToDoViewModel
import io.reactivex.schedulers.Schedulers.single
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // single instance of TodoRepository with db; looks for the database in the module
    single<TodoRepository> { TodoRepository(db = get()) }

    single {
        Room.databaseBuilder(
            androidApplication(),
            TodoDatabase::class.java,
            "TodoDatabase.db"
        )

            .build()
    }

    // Simple Presenter Factory
    viewModel { ToDoViewModel(todoRepository = get()) }
}
