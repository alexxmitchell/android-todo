package com.example.todo.application

import androidx.room.Room
import com.example.todo.db.TodoDatabase
import com.example.todo.db.TodoRepository
import com.example.todo.network.XkcdService
import com.example.todo.ui.ToDoViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


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

    single {
        //tells which client to make the call from
        //val client = OkHttpClient.Builder().build()
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://xkcd.com/")
            .build()
            .create(XkcdService::class.java)
    }

    // Simple Presenter Factory
    viewModel { ToDoViewModel(todoRepository = get()) }
}
