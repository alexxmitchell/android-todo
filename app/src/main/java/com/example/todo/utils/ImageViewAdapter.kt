package com.example.todo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load

object ImageViewAdapter {
    //JvmStatic - can be used in layout w/out importing; needed when adding viewAdapter
    @JvmStatic

    //@bindingAdapter - tells us that its not a regular function; its called automatically from the xml
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, url: String?) {
        imageView.load(url)
    }
}