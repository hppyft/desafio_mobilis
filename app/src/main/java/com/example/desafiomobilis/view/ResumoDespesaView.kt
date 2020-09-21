package com.example.desafiomobilis.view

import androidx.fragment.app.viewModels
import com.example.desafiomobilis.R
import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.viewmodel.ResumoDespesaViewModel
import com.example.desafiomobilis.viewmodel.ResumoMFViewModel

class ResumoDespesaView : ResumoMFView<Despesa>(){
    private val mViewModel: ResumoDespesaViewModel by viewModels()

    override fun getViewModel(): ResumoMFViewModel<Despesa> = mViewModel

    override fun getErrorText(): String {
        return getString(R.string.error_get_despesa_list_text)
    }

    override fun getMFTypeKey() = CriarView.DESPESA
}