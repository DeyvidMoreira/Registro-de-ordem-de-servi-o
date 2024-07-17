package com.example.tellcom.view.activitys

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.telecom.databinding.ActivityDateOrderBinding
import com.example.tellcom.view.adapter.OrderAdapter
import com.example.tellcom.viewModel.FormOrderViewModel

class DateOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDateOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}