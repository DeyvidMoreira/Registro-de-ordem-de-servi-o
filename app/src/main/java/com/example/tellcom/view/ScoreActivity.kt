package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.telecom.databinding.ActivityScoreBinding
import com.example.tellcom.viewModel.SetupScoreViewModel

class ScoreActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityScoreBinding
    private lateinit var viewModel: SetupScoreViewModel

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
                binding.tvSetPunctuation.text = latestScore.singlePoints
                binding.tvMetaSetScore.text = latestScore.metaPoints
            }
        })

        binding.fabAddScore.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id){
            binding.fabAddScore.id ->{
                val intent = Intent(applicationContext,SetupScoreActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}