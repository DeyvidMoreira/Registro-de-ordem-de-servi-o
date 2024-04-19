package com.example.tellcom.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.model.ScoreModel
import com.example.tellcom.service.repository.local.OrderDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetupScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val orderDao = OrderDatabase.getDatabase(application).orderDao()
    private val _isScoreSaved = MutableLiveData<Boolean>()
    val isScoreSaved: LiveData<Boolean> get() = _isScoreSaved

    private val _updateScore = MutableLiveData<Double>()
    val updateScore: LiveData<Double> get() = _updateScore

    //LiveData para o currentScore
    private var _currentscore = MutableLiveData<Double>()
    val currentScore: LiveData<Double> get() = _currentscore

    init {
        // Inicializa o currentScore
        _currentscore.value = 0.0
    }

    //Salva as características do Score
    fun saveScore(jobName: String, singlePoints: Double, metaPoints: Double) {
        val score = ScoreModel(
            jobName = jobName,
            singlePoints = singlePoints,
            metaPoints = metaPoints
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                orderDao.insertScore(score)
                _isScoreSaved.postValue(true)
            } catch (e: Exception) {
                _isScoreSaved.postValue(false)
                Log.e(
                    "${Constants.NOTIFICATION.SAVE_SCORE_ERROR_TAG}",
                    "${Constants.NOTIFICATION.SAVE_SCORE_ERROR_MESSAGE} ${e.message}"
                )
            }
        }
    }

    //Atualiza o Score
    fun updateScore(orderModel: List<OrderModel>, scoreModel: ScoreModel): Double {

        var currentPontuation = 0.0
        if (orderModel.isNotEmpty()){
            for (order in orderModel) {
                if (order.status == 1) {
                    if (currentScore != null) {
                        // Adicionar a pontuação individual ao total
                        currentPontuation += scoreModel.singlePoints
                    }
                }
            }
        }

        return currentPontuation
    }

    //Pega todos a lista de Score
    fun getAllScores(): LiveData<List<ScoreModel>> {
        return orderDao.getAllScore()
    }

    //Pega todas as ordens
    fun getAllOrders(): LiveData<List<OrderModel>> {
        return orderDao.getAllOrders()
    }

    //Passa o novo valor atualizado para o currentScore
    fun setCurrentScore(score: Double) {
        _currentscore.postValue(score)
    }

}