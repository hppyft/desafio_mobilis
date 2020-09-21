package com.example.desafiomobilis.view

import android.os.Bundle
import androidx.fragment.app.viewModels
import com.example.desafiomobilis.R
import com.example.desafiomobilis.model.Receita
import com.example.desafiomobilis.viewmodel.CriarReceitaViewModel

class CriarReceitaView : CriarView<Receita>() {

    companion object {
        @JvmStatic
        fun getInstance(id: String?): CriarReceitaView {
            return CriarReceitaView().apply {
                arguments = Bundle().apply {
                    putString(MF_ID_KEY, id)
                }
            }
        }
    }

    private val mViewModel: CriarReceitaViewModel by viewModels()

    override fun getViewModel() = mViewModel
    override fun getMF() = Receita()
    override fun getTitle() =
        if (mId != null) getString(R.string.criar_view_alteracao_receita_title) else getString(
            R.string.criar_view_nova_receita_title
        )

    override fun getEfetuadoLabel() = getString(R.string.recebido_label)
    override fun getNaoEfetuadoLabel() = getString(R.string.nao_recebido_label)
}