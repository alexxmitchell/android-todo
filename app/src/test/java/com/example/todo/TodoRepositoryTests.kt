package com.example.todo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.todo.db.RoomTodo
import com.example.todo.db.TodoDatabase
import com.example.todo.db.TodoRepository
import com.example.todo.ui.ToDoItem
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TodoRepositoryTests {
    //for the coroutine
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var todoDatabaseMockk: TodoDatabase // order doesn't matter

    private lateinit var repository: TodoRepository
    val liveDataListAllData = MutableLiveData<List<RoomTodo>>()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        every { todoDatabaseMockk.todoDao().getAll() } returns liveDataListAllData

        //initializes repo
        repository = TodoRepository(todoDatabaseMockk)
    }

    @Test
    fun getAllTest() {
        liveDataListAllData.postValue(
            listOf(
                RoomTodo(7238932975349L, "walking", false, "outdoors", "05/30/2020", 7238932975349L)
            )
        )
        val list = listOf(
            RoomTodo(7238932975349L, "walking", false, "outdoors", "05/30/2020", 7238932975349L)
        )
        assertEquals(list, todoDatabaseMockk.todoDao().getAll().value)
    }

    @Test
    fun mapRoomTodoToTodoItemTest() {
        val roomTodo = RoomTodo(73498892395L, "walking", false, "outdoors", "05/30/2020", 7238932975349L)
        val todo = ToDoItem("walking", false, "outdoors", "05/30/2020", 7238932975349L)
        assertEquals(todo, repository.mapRoomTodoToTodoItem(roomTodo))
    }

    @Test
    fun mapTodoItemToRoomTodoTest() {
        val roomTodo = RoomTodo(System.currentTimeMillis(), "walking", false, "outdoors", "05/30/2020", 7238932975349L)
        val todo = ToDoItem("walking", false, "outdoors", "05/30/2020", 7238932975349L)
        assertEquals(roomTodo, repository.mapTodoItemToRoomTodo(todo))
    }

    @Test
    fun insertTodoTest() {
        val roomTodo = RoomTodo(System.currentTimeMillis(), "walking", false, "outdoors", "05/30/2020", 7238932975349L)
        val todo = ToDoItem("walking", false, "outdoors", "05/30/2020", 7238932975349L)
        every { todoDatabaseMockk.todoDao().insertOne(any()) } returns System.currentTimeMillis()
        runBlocking {
            repository.insertTodo(todo)
        }

        verify { todoDatabaseMockk.todoDao().insertOne(roomTodo) }
    }



}