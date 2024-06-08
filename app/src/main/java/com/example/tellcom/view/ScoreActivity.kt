package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.telecom.databinding.ActivityScoreBinding
import com.example.tellcom.service.model.ScoreModel
import com.example.tellcom.viewModel.SetupScoreViewModel

class ScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding
    private lateinit var viewModel: SetupScoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Iniciando o ViewModel
        viewModel = ViewModelProvider(this)[SetupScoreViewModel::class.java]

        // Observando as mudanças nos dados combinados e atualizando a UI
        viewModel.combinedData.observe(this) { (scores, orders) ->
            if (!scores.isNullOrEmpty() && orders != null) {
                val score = scores.first()
                // Atualizar a UI com a primeira pontuação disponível
                updateUI(score)
                // Calcular e atualizar o score atual com base nas ordens
                viewModel.calculateAndUpdateSCore(orders, score)
            }
        }

        // Observando as mudanças no currentScore e atualizando a UI
        viewModel.currentScore.observe(this) { newScore ->
            binding.tvCurrentScore.text = newScore.toString()
        }

        // View de criação de score
        binding.fabAddScore.setOnClickListener {
            startActivity(Intent(this, SetupScoreActivity::class.java))
            finish()
        }
    }

    private fun updateUI(scoreModel: ScoreModel) {
        binding.tvJobName.text = scoreModel.jobName
        binding.tvSetPunctuation.text = scoreModel.singlePoints.toString()
        binding.tvMetaSetScore.text = scoreModel.metaScore.toString()
    }
}
