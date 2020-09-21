package com.example.desafiomobilis.viewmodel

import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {
    fun onLoginBtnClicked(startActivity: () -> Unit) {
        startActivity()
    }
}