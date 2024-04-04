package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.telecom.databinding.ActivityScoreBinding
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.viewModel.SetupScoreViewModel
import com.google.android.material.snackbar.Snackbar

class ScoreActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityScoreBinding
    private lateinit var viewModel: SetupScoreViewModel
    private lateinit var orderModel: OrderModel
    private var totalScore: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //inicia o viewModel
        viewModel = ViewModelProvider(this).get(SetupScoreViewModel::class.java)

        //Inicializando o OrderModel com o último score disponível
        initializeOrderModel()

        // Observar as mudanças nos dados e atualizar a UI
        viewModel.getAllScores().observe(this, Observer { scores ->
            if (scores.isNotEmpty()) {
                val latestScore = scores.last()
                binding.tvJobName.text = latestScore.jobName
                binding.tvSetPunctuation.text = latestScore.singlePoints.toString()
                binding.tvMetaSetScore.text = latestScore.metaPoints.toString()
                totalScore = latestScore.currentScore.toDouble()

                //Atualiza o currentScore na UI
                updateCurrentScore()
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

    //Método para inicializar o OrderModel
    private fun initializeOrderModel() {
        orderModel = OrderModel(
            protocolNumber = "",
            clientName = "",
            dropValue = "",
            status = 3
        )
    }

    //Método para atualizar o currentScore na UI
    private fun updateCurrentScore() {
        //Verifica se foi inicializado o orderModel
        if (::orderModel.isInitialized) {
            val singlePoints = binding.tvSetPunctuation.text.toString().toDoubleOrNull() ?: 0.0
            val newScore = if (orderModel.status == 1) {
                totalScore + singlePoints
            } else {
                totalScore
            }
            binding.tvCurrentScore.text = newScore.toString()
        } else {
            //Snackbar caso o orderModel não tenha sido inicializado
            Snackbar.make(
                binding.root,
                "${Constants.NOTIFICATION.ORDER_MODEL_FAIL_INITIALIZE}",
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }

}