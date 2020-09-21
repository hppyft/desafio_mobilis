package com.example.desafiomobilis.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.desafiomobilis.R
import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.viewmodel.CriarDespesaViewModel

class CriarDespesaView : CriarView<Despesa>() {

    companion object {
        @JvmStatic
        fun getInstance(id: String?): CriarDespesaView {
            return CriarDespesaView().apply {
                arguments = Bundle().apply {
                    putString(MF_ID_KEY, id)
                }
            }
        }
    }

    private val mViewModel: CriarDespesaViewModel by viewModels()

    override fun getViewModel() = mViewModel
    override fun getMF() = Despesa()
    override fun getTitle() =
        if (mId != null) getString(R.string.criar_view_alteracao_despesa_title) else getString(
            R.string.criar_view_nova_despesa_title
        )

    override fun getEfetuadoLabel() = getString(R.string.pago_label)
    override fun getNaoEfetuadoLabel() = getString(R.string.nao_pago_label)
}