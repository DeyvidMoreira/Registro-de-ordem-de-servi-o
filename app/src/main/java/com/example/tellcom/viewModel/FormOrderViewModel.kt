package com.example.tellcom.viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tellcom.service.constants.ConstantsOrders
import com.example.tellcom.service.model.OrderModel
import com.example.tellcom.service.repository.local.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.Exception

class FormOrderViewModel(application: Application) : AndroidViewModel(application) {

    private val appDao = AppDatabase.getDatabase(application).appDao()
    private val _isOrderSaved = MutableLiveData<Boolean>()
    val allOrders: LiveData<List<OrderModel>> = appDao.getAllOrders()
    val isOrderSaved: LiveData<Boolean> get() = _isOrderSaved

    //LiveData para observar o evento de atualização da ordem
    private val _orderUpdateEvent = MutableLiveData<Unit>()

    //Função para salvar os detalhes das OS
    fun saveOrder(protocolNumber: String, clientName: String, dropValue: String, date:Long) {
        val order = OrderModel(
            protocolNumber = protocolNumber,
            clientName = clientName,
            dropValue = dropValue,
            date = date
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                appDao.insertOrders(order)
                _isOrderSaved.postValue(true)
                //Dispara evento de atualização
                _orderUpdateEvent.postValue(Unit)
            } catch (e: Exception) {
                try {
                    appDao.insertOrders(order)
                    _isOrderSaved.postValue(true)
                } catch (e: Exception) {
                    _isOrderSaved.postValue(false)
                    Log.e(
                        ConstantsOrders.ORDERS.SAVE_ORDER_ERROR_TAG,
                        "${ConstantsOrders.ORDERS.SAVE_ORDER_ERROR_MESSAGE} ${e.message}"
                    )
                }
                _isOrderSaved.postValue(false)
            }
        }
    }

    //Função para excluir item da lista
    fun deleteOrder(order: OrderModel){
        viewModelScope.launch (Dispatchers.IO){
            try {
                appDao.deleteOrderById(order.id)
            }catch (e:Exception){
                Log.e(
                    ConstantsOrders.ORDERS.DELETE_ORDER_ERROR_TAG,
                    ConstantsOrders.ORDERS.DELETE_ORDER_ERROR_MESSAGE
                )
            }
        }
    }

    //Função para atualizar a lista de ordens
    fun updateOrder(order: OrderModel) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                appDao.updateOrders(order)
                //Dispara evento de atualização
                _orderUpdateEvent.postValue(Unit)
            } catch (e: Exception) {
                Log.e(
                    ConstantsOrders.ORDERS.UPDATE_ORDER_ERROR_TAG,
                    "${ConstantsOrders.ORDERS.UPDATE_ORDER_ERROR_MESSAGE} ${e.message}"
                )
            }
        }
    }

}