package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.telecom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnOrders.setOnClickListener(this)
        binding.btnNewOrder.setOnClickListener(this)
        binding.btnScore.setOnClickListener(this)
        binding.btnNewScore.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {

            binding.btnOrders.id -> {
                val intent = Intent(applicationContext, OrderActivity::class.java)
                startActivity(intent)
            }
            binding.btnScore.id -> {
                val intent = Intent(applicationContext, ScoreActivity::class.java)
                startActivity(intent)
            }
            binding.btnNewOrder.id -> {
                val intent = Intent(applicationContext, FormOrderActivity::class.java)
                startActivity(intent)
            }
            binding.btnNewScore.id -> {
                val intent = Intent(applicationContext, SetupScoreActivity::class.java)
                startActivity(intent)
            }

        }
    }


}