package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.telecom.databinding.ActivityScoreBinding
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.model.ScoreModel
import com.example.tellcom.view.adapter.OrderAdapter
import com.example.tellcom.viewModel.SetupScoreViewModel

class ScoreActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityScoreBinding
    private lateinit var viewModel: SetupScoreViewModel
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var orderModel: OrderModel
    private var totalScore: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicia o viewModel
        viewModel = ViewModelProvider(this).get(SetupScoreViewModel::class.java)

        // Observar as mudanças nos dados e atualizar a UI
        viewModel.getAllScores().observe(this, Observer { scores ->
            if (scores.isNotEmpty()) {
                val latestScore = scores.last()
                binding.tvJobName.text = latestScore.jobName
                binding.tvSetPunctuation.text = latestScore.singlePoints.toString()
                binding.tvMetaSetScore.text = latestScore.metaPoints.toString()
                totalScore = latestScore.currentScore.toDouble()
                //Atualiza o currentScore na UI
                updateCurrentScore(totalScore)
            }
        })


        binding.fabAddScore.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.fabAddScore.id -> {
                val intent = Intent(applicationContext, SetupScoreActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    //Método para atualizar o currentScore na UI
    private fun updateCurrentScore(score: Double) {
        val singlePoints = binding.tvSetPunctuation.text.toString().toDoubleOrNull() ?: 0.0
        val newScore = if (orderModel.status == 1) {
            score + singlePoints
        } else {
            score
        }
        binding.tvCurrentScore.text = newScore.toString()
    }

}