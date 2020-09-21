package com.example.desafiomobilis

import androidx.lifecycle.*
import java.util.*

class ResumoViewModel : ViewModel(), LifecycleObserver {

    private val mDespesaList = MutableLiveData<List<Despesa>>()

    fun getDespesaList(): LiveData<List<Despesa>> = mDespesaList

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    private fun onCreate(){
        mDespesaList.postValue(createDummyData())
    }

    private fun createDummyData():List<Despesa> {
        val list = mutableListOf<Despesa>()
        for (x in 1..21){
            val despesa = Despesa(data = Date(), valor = 1250.2 + x,descricao = "Compra $x de um balde", pago = true)
            list.add(despesa)
        }
        return list
    }

    fun onCriarClicked(startActivity: () -> Unit) {
        startActivity()
    }
}