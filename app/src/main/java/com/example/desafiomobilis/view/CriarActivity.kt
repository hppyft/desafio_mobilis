package com.example.desafiomobilis.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.desafiomobilis.R
import com.example.desafiomobilis.util.addFragmentTo
import com.example.desafiomobilis.util.getBundle
import java.lang.Exception

class CriarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_criacao)
        addFragment()

    }

    private fun addFragment() {
        val bundle = intent.getBundle()
        val mf: Int = bundle.getInt(CriarView.MF_KEY, -1)
        val mfId: String? = bundle.getString(CriarView.MF_ID_KEY, null)
        when (mf) {
            CriarView.DESPESA ->
                addFragmentTo(R.id.frag_container, CriarDespesaView.getInstance(mfId))
            CriarView.RECEITA ->
                addFragmentTo(R.id.frag_container, CriarReceitaView.getInstance(mfId))
            else -> throw Exception("Necessita da chave de MF para instanciar CriarView")
        }
    }
}