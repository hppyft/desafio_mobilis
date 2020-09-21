package com.example.desafiomobilis.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.desafiomobilis.R
import com.example.desafiomobilis.util.addFragmentTo

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addFragmentTo(R.id.frag_container, createLoginFragment())
    }

    private fun createLoginFragment() =
        LoginView()
}
