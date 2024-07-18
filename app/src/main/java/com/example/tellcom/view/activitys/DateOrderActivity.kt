package com.example.tellcom.view.activitys

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.telecom.databinding.ActivityDateOrderBinding

class DateOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDateOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}