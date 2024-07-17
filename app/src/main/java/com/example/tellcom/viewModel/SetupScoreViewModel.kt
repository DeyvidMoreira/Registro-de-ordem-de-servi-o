package com.example.tellcom.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.model.ScoreModel
import com.example.tellcom.service.repository.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetupScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val appDao = AppDatabase.getDatabase(application).appDao()
    private val _isScoreSaved = MutableLiveData<Boolean>()
    val isScoreSaved: LiveData<Boolean> get() = _isScoreSaved


    //LiveData para o currentScore
    private var _currentscore = MutableLiveData<Double>()
    val currentScore: LiveData<Double> get() = _currentscore

    private val allScores: LiveData<List<ScoreModel>> = appDao.getAllScore()
    private val allOrders: LiveData<List<OrderModel>> = appDao.getAllOrders()

    //MediatorLiveData para combinar o score e as ordens
    private val _combinedData = MediatorLiveData<Pair<List<ScoreModel>?, List<OrderModel>?>>()
        .apply {
            addSource(allScores) { score ->
                value = Pair(score, allOrders.value)
            }
            addSource(allOrders) { orders ->
                value = Pair(allScores.value, orders)
            }
        }
    val combinedData: LiveData<Pair<List<ScoreModel>?, List<OrderModel>?>> get() = _combinedData


    init {
        // Inicializa o currentScore
        _currentscore.value = 0.0
    }

    //Função para salvar o score
    fun saveScore(jobName: String, singlePoints: Double, metaScore: Double) {
        val score = ScoreModel(
            jobName = jobName,
            singlePoints = singlePoints,
            metaScore = metaScore
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                appDao.insertScore(score)
                _isScoreSaved.postValue(true)
            } catch (e: Exception) {
                _isScoreSaved.postValue(false)
                Log.e(
                    Constants.NOTIFICATION.SAVE_SCORE_ERROR_TAG,
                    "${Constants.NOTIFICATION.SAVE_SCORE_ERROR_MESSAGE} ${e.message}"
                )
            }
        }
    }

    //Função para somar e atualizar o score
    fun calculateAndUpdateSCore(orderModel: List<OrderModel>, scoreModel: ScoreModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val newScore = orderModel.filter { it.status == 1 }
                .sumOf { scoreModel.singlePoints }
            _currentscore.postValue(newScore)
        }

    }

}