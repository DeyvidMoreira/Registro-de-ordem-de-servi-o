package com.example.tellcom.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.repository.OrderDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class FormOrderViewModel(application: Application) : AndroidViewModel(application) {

    private val orderDao = OrderDatabase.getDatabase(application).orderDao()
    private val _isOrderSaved = MutableLiveData<Boolean>()
    val isOrderSaved: LiveData<Boolean> get() = _isOrderSaved
    val allOrders: LiveData<List<OrderModel>> = orderDao.getAllOrders()

    fun saveOrder(protocolNumber: String, clientName: String, dropValue: String) {
        val order = OrderModel(
            protocolNumber = protocolNumber,
            clientName = clientName,
            dropValue = dropValue
        )
        GlobalScope.launch(Dispatchers.IO) {
            try {
                orderDao.insert(order)
                _isOrderSaved.postValue(true)
            } catch (e: Exception) {
                try {
                    orderDao.insert(order)
                    _isOrderSaved.postValue(true)
                } catch (e: Exception) {
                    _isOrderSaved.postValue(false)
                    Log.e("${Constants.NOTIFICATION.SAVE_ERROR_TAG}",
                        "${Constants.NOTIFICATION.SAVE_ERROR_MESSAGE} ${e.message}")
                }
                _isOrderSaved.postValue(false)
            }
        }
    }


    fun updateOrder(order: OrderModel){
        GlobalScope.launch (Dispatchers.IO) {
            try {
                orderDao.update(order)
            }catch (e:Exception){
                Log.e("${Constants.NOTIFICATION.UPDATE_ERROR_TAG}",
                    "${Constants.NOTIFICATION.UPDATE_ERROR_MESSAGE} ${e.message}")
            }
        }
    }


}