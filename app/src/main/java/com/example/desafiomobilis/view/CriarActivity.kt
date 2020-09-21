package com.example.desafiomobilis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desafiomobilis.R
import com.example.desafiomobilis.util.addFragmentTo
import com.example.desafiomobilis.util.getBundle

class CriarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criacao)
        addFragmentTo(R.id.frag_container, createCriacaoFragment())
    }

    private fun createCriacaoFragment(): CriarView {
        val bundle = intent.getBundle()
        if (bundle.containsKey(CriarView.DESPESA_ID_KEY)) {
            return CriarView.getInstance(bundle.getString(CriarView.DESPESA_ID_KEY,null))
        } else {
            return CriarView()
        }
    }
}