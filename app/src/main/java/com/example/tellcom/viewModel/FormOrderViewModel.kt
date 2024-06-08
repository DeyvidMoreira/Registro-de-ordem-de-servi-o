package com.example.tellcom.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.repository.local.OrderDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

class FormOrderViewModel(application: Application) : AndroidViewModel(application) {

    private val orderDao = OrderDatabase.getDatabase(application).orderDao()
    private val _isOrderSaved = MutableLiveData<Boolean>()
    val allOrders: LiveData<List<OrderModel>> = orderDao.getAllOrders()
    val isOrderSaved: LiveData<Boolean> get() = _isOrderSaved

    //LiveData para observar o evento de atualização da ordem
    private val _orderUpdateEvent = MutableLiveData<Unit>()

    //Função para salvar os detalhes das OS

    fun saveOrder(protocolNumber: String, clientName: String, dropValue: String) {
        val order = OrderModel(
            protocolNumber = protocolNumber,
            clientName = clientName,
            dropValue = dropValue
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                orderDao.insertOrders(order)
                _isOrderSaved.postValue(true)
                //Dispara evento de atualização
                _orderUpdateEvent.postValue(Unit)
            } catch (e: Exception) {
                try {
                    orderDao.insertOrders(order)
                    _isOrderSaved.postValue(true)
                } catch (e: Exception) {
                    _isOrderSaved.postValue(false)
                    Log.e(
                        Constants.NOTIFICATION.SAVE_ORDER_ERROR_TAG,
                        "${Constants.NOTIFICATION.SAVE_ORDER_ERROR_MESSAGE} ${e.message}"
                    )
                }
                _isOrderSaved.postValue(false)
            }
        }
    }

    //Função para atualizar a lista de ordens
    fun updateOrder(order: OrderModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                orderDao.updateOrders(order)
                //Dispara evento de atualização
                _orderUpdateEvent.postValue(Unit)
            } catch (e: Exception) {
                Log.e(
                    Constants.NOTIFICATION.UPDATE_ORDER_ERROR_TAG,
                    "${Constants.NOTIFICATION.UPDATE_ORDER_ERROR_MESSAGE} ${e.message}"
                )
            }
        }
    }

}