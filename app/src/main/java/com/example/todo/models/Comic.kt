package com.example.todo.models

import com.squareup.moshi.Json

data class Comic (
    //map object class to json class; all variables required to build Comic class
    @Json(name = "month") val comicMonth: String,
    @Json(name = "num") val comicId: Int,
    @Json(name = "year") val comicYear: String,
    @Json(name = "title") val comicTitle: String,
    @Json(name = "alt") val comicAltText: String,
    @Json(name = "img") val comicImg: String,
    @Json(name = "day") val comicDay: String
){
    fun getFullDate(): String {
        return String.format("%s/%s/%s", comicMonth, comicDay, comicYear)
    }
}