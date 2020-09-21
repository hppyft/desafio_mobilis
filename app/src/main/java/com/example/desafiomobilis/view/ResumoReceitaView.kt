package com.example.desafiomobilis.view

import androidx.fragment.app.viewModels
import com.example.desafiomobilis.R
import com.example.desafiomobilis.model.Receita
import com.example.desafiomobilis.viewmodel.ResumoMFViewModel
import com.example.desafiomobilis.viewmodel.ResumoReceitaViewModel

class ResumoReceitaView : ResumoMFView<Receita>() {
    private val mViewModel: ResumoReceitaViewModel by viewModels()

    override fun getViewModel(): ResumoMFViewModel<Receita> = mViewModel

    override fun getErrorText(): String {
        return getString(R.string.error_get_receita_list_text)
    }

    override fun getMFTypeKey() = CriarView.RECEITA
}