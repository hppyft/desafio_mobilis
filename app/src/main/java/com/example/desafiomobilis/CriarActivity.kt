package com.example.desafiomobilis

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class CriarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criacao)
        addFragmentTo(R.id.frag_container, createCriacaoFragment())
    }

    private fun createCriacaoFragment() = CriarView()
}