package com.example.desafiomobilis

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.desafiomobilis.databinding.DespesaItemBinding

class DespesaAdapter : RecyclerView.Adapter<DespesaAdapter.ViewHolder>() {

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
        holder.mBinding.data = item.data.toFormattedString()
        holder.mBinding.valor = item.valor.toReal()
        holder.mBinding.descricao = item.descricao
        holder.mBinding.pago = if (item.pago) "Pago" else "Não Pago" //TODO
    }

    fun setList(list:List<Despesa>){
        mList=list
        notifyDataSetChanged()
    }
}