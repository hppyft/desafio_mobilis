package com.example.desafiomobilis

import androidx.lifecycle.ViewModel

class LoginViewModel() : ViewModel() {
    fun onLoginBtnClicked(startActivity: () -> Unit) {
        startActivity()
    }
}