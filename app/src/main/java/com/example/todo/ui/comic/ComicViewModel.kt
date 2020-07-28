package com.example.todo.ui.comic

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.models.Comic
import com.example.todo.network.XkcdService
import kotlinx.coroutines.launch
import kotlin.random.Random

//injecting the xkcdService (webservice) from the modules
class ComicViewModel( private val xkcdService: XkcdService) : ViewModel() {
    val comic = MutableLiveData<Comic>(null)
    var maxNumber = 0

     fun getCurrentComic(){
         //putting the coroutine inside of function
         viewModelScope.launch {
             val currentComic = xkcdService.getCurrentComic()
             maxNumber = currentComic.comicId
             comic.postValue(currentComic)

         }

    }

    fun getRandomComic(){
        viewModelScope.launch {
            val currentComic = xkcdService.getSpecificComic((0..maxNumber).random().toString())
            comic.postValue(currentComic)

        }
    }
}