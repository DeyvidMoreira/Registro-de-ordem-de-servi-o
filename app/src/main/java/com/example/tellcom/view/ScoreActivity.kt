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
import com.example.tellcom.service.repository.dao.OrderDao
import com.example.tellcom.service.repository.local.OrderDatabase
import com.example.tellcom.viewModel.SetupScoreViewModel

class ScoreActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityScoreBinding
    private lateinit var viewModel: SetupScoreViewModel
    private lateinit var orderDao: OrderDao
    private lateinit var scoreModel: ScoreModel
    private var totalScore: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializando o DAO
        orderDao = OrderDatabase.getDatabase(application).orderDao()

        // Iniciando o ViewModel
        viewModel = ViewModelProvider(this).get(SetupScoreViewModel::class.java)

        // Observando as mudanças nos dados e atualizando a UI
        viewModel.getAllScores().observe(this, Observer { scores ->
            if (scores.isNotEmpty()) {
                val latestScore = scores.last()
                binding.tvJobName.text = latestScore.jobName
                binding.tvSetPunctuation.text = latestScore.singlePoints.toString()
                binding.tvMetaSetScore.text = latestScore.metaPoints.toString()
                totalScore = latestScore.currentScore

                // Inicia o ScoreModel
                scoreModel = latestScore

                // Atualiza o totalScore
                viewModel.setCurrentScore(totalScore)

                // Obter o valor atual do LiveData de lista de OrderModel
                val orderList: List<OrderModel> = orderDao.getAllOrders().value ?: emptyList()

                // Chamar a função updateScore do ViewModel
                val updateScore = viewModel.updateScore(orderList, scoreModel)
                totalScore = updateScore
                binding.tvCurrentScore.text = totalScore.toString()
            }
        })

        // Atualizar a TextView com o valor de currentPontuation
        viewModel.updateScore.observe(this, Observer { score ->
            binding.tvCurrentScore.text = score.toString()
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
}
