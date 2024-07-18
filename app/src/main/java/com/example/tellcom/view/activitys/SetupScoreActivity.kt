package com.example.tellcom.view.activitys

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.telecom.databinding.ActivitySetupScoreBinding
import com.example.tellcom.service.constants.ConstantsScore
import com.example.tellcom.service.constants.ConstantsValidation
import com.example.tellcom.viewModel.SetupScoreViewModel
import kotlinx.coroutines.launch

class SetupScoreActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySetupScoreBinding
    private lateinit var viewModel: SetupScoreViewModel

    private var jobName: String = ""
    private var singlePoints: Double = 0.0
    private var metaScore: Double = 0.0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicialize a classe NOTIFICATION com o contexto desta atividade
        ConstantsScore.SCORE.context = this

        // Iniciando a viewModel
        viewModel = ViewModelProvider(this)[SetupScoreViewModel::class.java]

        setListeners()
        observers()
    }

    private fun fillingInFields() {
        val jobName = binding.etJobName.text.toString().trim()
        val singlePointsText = binding.etScoreValue.text.toString().trim()
        val metaScoreText = binding.etMetaScore.text.toString().trim()

        if (jobName.isEmpty() || singlePointsText.isEmpty() || metaScoreText.isEmpty()) {
            Toast.makeText(
                this,
                ConstantsValidation.VALIDATION.FILLING_IN_FILDS_NOTIFICATION,
                Toast.LENGTH_SHORT
            ).show()
        } else {
            try {
                val singlePoints = singlePointsText.toDouble()
                val metaScore = metaScoreText.toDouble()

                viewModel.viewModelScope.launch {
                    viewModel.saveScore(jobName, singlePoints, metaScore)
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    this,
                    ConstantsScore.SCORE.NUMBER_FORMAT_ERROR,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnSaveScore.id -> {
                fillingInFields()
            }
        }
    }

    private fun setListeners() {
        binding.btnSaveScore.setOnClickListener(this)
    }

    private fun observers() {
        viewModel.isScoreSaved.observe(this) { isSaved ->
            if (isSaved) {
                Toast.makeText(
                    this,
                    ConstantsScore.SCORE.SAVED_SCORE,
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(applicationContext, ScoreActivity::class.java)
                intent.putExtra("jobName", jobName)
                intent.putExtra("singlePoints", singlePoints)
                intent.putExtra("metaScore", metaScore)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    ConstantsScore.SCORE.NOT_SAVED_SCORE,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}