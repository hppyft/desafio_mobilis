package com.example.desafiomobilis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desafiomobilis.R
import com.example.desafiomobilis.util.addFragmentTo

class ResumoActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumo)
        addFragmentTo(R.id.frag_container, createResumoDespesaFragment())
    }

    private fun createResumoDespesaFragment() =
        ResumoDespesaView()
}