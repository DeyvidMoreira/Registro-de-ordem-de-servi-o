package com.example.tellcom.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.service.model.ScoreModel
import com.example.tellcom.service.repository.local.ScoreDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SetupScoreViewModel(application: Application) : AndroidViewModel(application) {

    private val scoreDao = ScoreDatabase.getDatabase(application).scoreDao()
    private val _isScoreSaved = MutableLiveData<Boolean>()

    val isScoreSaved: LiveData<Boolean> get() = _isScoreSaved

    fun saveScore(jobName: String, singlePoints: String, metaPoints: String) {
        val score = ScoreModel(
            jobName = jobName,
            singlePoints = singlePoints,
            metaPoints = metaPoints
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                scoreDao.insert(score)
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

    fun getAllScores(): LiveData<List<ScoreModel>> {
        return scoreDao.getAllScore()
    }

    fun updateScore(score: ScoreModel) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                scoreDao.update(score)
            } catch (e: Exception) {
                Log.e(
                    "${Constants.NOTIFICATION.UPDATE_SCORE_ERROR_TAG}",
                    "${Constants.NOTIFICATION.UPDATE_SCORE_ERROR_MESSAGE} ${e.message}"
                )
            }
        }
    }

}