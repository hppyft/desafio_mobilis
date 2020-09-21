package com.example.desafiomobilis.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilis.databinding.MfItemBinding
import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.model.MovimentacaoFinanceira
import com.example.desafiomobilis.util.setSafeOnClickListener
import com.example.desafiomobilis.util.toFormattedString
import com.example.desafiomobilis.util.toReal

class MFAdapter(val mOnMoreOptionsClicked: (String, View) -> Unit) :
    RecyclerView.Adapter<MFAdapter.ViewHolder>() {

    private var mList: List<MovimentacaoFinanceira> = listOf()

    class ViewHolder(val mBinding: MfItemBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = MfItemBinding.inflate(layoutInflator, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.mBinding.data = item.data?.toFormattedString()
        holder.mBinding.valor = item.valor?.toReal()
        holder.mBinding.descricao = item.descricao
        holder.mBinding.efetuado =
            if (item.efetuado != null && item.efetuado!!) item.getEfetuadoString() else item.getNaoEfetuadoString()
        holder.mBinding.moreOptions.setSafeOnClickListener {
            item.id?.let { id ->
                mOnMoreOptionsClicked(id, it)
            }
        }
    }

    fun setList(list: List<MovimentacaoFinanceira>) {
        mList = list
        notifyDataSetChanged()
    }
}