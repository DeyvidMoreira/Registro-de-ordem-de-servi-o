package com.example.tellcom.view.activitys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.telecom.databinding.ActivityRoutesBinding

class RoutesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRoutesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoutesBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }



}