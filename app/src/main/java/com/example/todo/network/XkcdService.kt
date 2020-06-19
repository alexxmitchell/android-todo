package com.example.todo.network

import com.example.todo.models.Comic
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface XkcdService {
    //not a function, @Get is an annotation
    @GET("info.0.json")
    //retrofit builds the function body, we don't have to
    //Call - async return type
    fun getCurrentComic(): Call<Comic>


    @GET("{comic}/info.0.json")
    fun getSpecificComic(@Path("comic") comic: String): Call<Comic>
}

//have to give the Retrofit builder the base url
// / always goes on the base url
//interface - a set of functions that aren't implemented in the file; no variables