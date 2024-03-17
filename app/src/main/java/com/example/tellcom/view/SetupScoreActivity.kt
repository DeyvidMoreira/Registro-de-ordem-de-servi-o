package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.telecom.databinding.ActivitySetupScoreBinding
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.viewModel.SetupScoreViewModel
import kotlinx.coroutines.launch

class SetupScoreActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySetupScoreBinding
    private lateinit var viewModel: SetupScoreViewModel

    var jobName: String = ""
    var singlePoints: String = ""
    var metaScore: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySetupScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //iniciando a viewModel
        viewModel = ViewModelProvider(this).get(SetupScoreViewModel::class.java)

        setListeners()
        observers()

    }

    private fun fillingInFields() {

        jobName = binding.etJobName.text.toString()
        singlePoints = binding.etScoreValue.text.toString()
        metaScore = binding.etMetaScore.text.toString()

        if (jobName.isEmpty() || singlePoints.isEmpty() || metaScore.isEmpty()) {
            Toast.makeText(
                this,
                "${Constants.NOTIFICATION.FILLING_IN_FILDS_NOTIFICATION}",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            viewModel.viewModelScope.launch {
                viewModel.saveScore(jobName, singlePoints, metaScore)
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
        viewModel.isScoreSaved.observe(this, Observer { isSaved ->
            if (isSaved) {
                Toast.makeText(
                    this,
                    "${Constants.NOTIFICATION.SAVED_SCORE}",
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
                    "${Constants.NOTIFICATION.NOT_SAVED_SCORE}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}