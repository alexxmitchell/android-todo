package com.example.todo.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.R

class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i("Fragment", "Logo Fragment created")
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }
    override fun onResume() {
        super.onResume()

        var startingDelay = Handler()
        startingDelay.postDelayed(navigateToToDos, 2000)
        Log.i("Fragment", "Ran onResume")
    }

    private var navigateToToDos = Runnable {
        findNavController().navigate(R.id.to_do_fragment)
    }


}