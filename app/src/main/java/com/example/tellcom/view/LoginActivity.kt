package com.example.tellcom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.telecom.databinding.ActivityLoginBinding
import com.example.tellcom.viewModel.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


        //Eventos
        binding.btnLogin.setOnClickListener(this)
        binding.etLoginGoogle.setOnClickListener(this)
        binding.etForgetPassword.setOnClickListener(this)
        binding.etRegister.setOnClickListener(this)

        //Observadores
        observe()


    }

    override fun onClick(v: View?) {

    }

    private fun observe() {

    }
}
