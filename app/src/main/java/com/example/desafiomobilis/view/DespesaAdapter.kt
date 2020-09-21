package com.example.desafiomobilis.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilis.databinding.DespesaItemBinding
import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.util.setSafeOnClickListener
import com.example.desafiomobilis.util.toFormattedString
import com.example.desafiomobilis.util.toReal

class DespesaAdapter(val mOnItemClick:(String) -> Unit) : RecyclerView.Adapter<DespesaAdapter.ViewHolder>() {

    private var mList: List<Despesa> = listOf()

    class ViewHolder(val mBinding: DespesaItemBinding) : RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = DespesaItemBinding.inflate(layoutInflator, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.mBinding.data = item.data?.toFormattedString()
        holder.mBinding.valor = item.valor?.toReal()
        holder.mBinding.descricao = item.descricao
        holder.mBinding.pago = if (item.pago != null && item.pago!!) "Pago" else "Não Pago" //TODO
        holder.mBinding.background.setSafeOnClickListener {
            item.id?.let{
                mOnItemClick(it)
            }
        }
    }

    fun setList(list: List<Despesa>) {
        mList = list
        notifyDataSetChanged()
    }
}