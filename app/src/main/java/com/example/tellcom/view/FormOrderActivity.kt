package com.example.tellcom.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.telecom.databinding.ActivityFormOrderBinding
import com.example.tellcom.service.constants.Constants
import com.example.tellcom.viewModel.FormOrderViewModel
import kotlinx.coroutines.launch

class FormOrderActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityFormOrderBinding
    private lateinit var viewModel: FormOrderViewModel
    var protocolName: String = ""
    var protocolNumber: String = ""
    var dropValue: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(FormOrderViewModel::class.java)

        //Eventos
        setListeners()
        //Observador
        observers()

    }

    //Verificação de campos
    private fun fillingInFields() {

        protocolName = binding.etProtocolName.text.toString()
        protocolNumber = binding.etProtocolNumber.text.toString()
        dropValue = binding.etDrop.text.toString()

        if (protocolName.isEmpty() || protocolNumber.isEmpty() || dropValue.isEmpty()) {
            Toast.makeText(
                this,
                Constants.NOTIFICATION.FILLING_IN_FILDS_NOTIFICATION,
                Toast.LENGTH_LONG
            ).show()
        } else {
            /*  Verificando se o protocolNumber é um valor númerico antes de converter para Long
                Verificando se a string inteira é numérica
                Verificando se o protocolNumber tem exatamente 8 caracteres
            * */
            if (protocolNumber.matches(Regex("[0-9]+")) && protocolNumber.length == 8) {
                //executando dentro das coroutines, para salvar a ordem de serviço
                viewModel.viewModelScope.launch {
                    viewModel.saveOrder(protocolNumber, protocolName, dropValue)
                }
            } else {
                if (protocolNumber.length != 8) {
                    Toast.makeText(
                        this, Constants.NOTIFICATION.PROTOCOL_NUMBER_REQUERIED,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        Constants.NOTIFICATION.INVALID_PROTOCOL_NUMBER,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.btnSaveOrder.id -> {
                fillingInFields()
            }
        }
    }


    private fun setListeners() {
        binding.btnSaveOrder.setOnClickListener(this)
    }

    //Obsrvador da verificação de ordem salva
    private fun observers() {
        viewModel.isOrderSaved.observe(this, Observer { isSaved ->
            if (isSaved) {
                Toast.makeText(
                    this,
                    Constants.NOTIFICATION.SAVED_ORDER,
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent(applicationContext, OrderActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(
                    this,
                    Constants.NOTIFICATION.NOT_SAVED_ORDER,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

}