package com.example.desafiomobilis.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.repository.DespesaRepository

class CriarViewModel : ViewModel() {

    private val mDespesa = MutableLiveData<Despesa?>()

    fun getDespesa():LiveData<Despesa?> = mDespesa

    fun onFinalizarClicked(
        despesa: Despesa,
        finishActivity: () -> Unit
    ) {
        if (despesa.id != null){
            DespesaRepository.update(despesa, {
                Log.d(TAG, "Despesa atualizada com sucesso")
            }, {
                Log.d(TAG, "Erro ao adicionar despesa") //TODO
            })
        } else{
            DespesaRepository.add(despesa, {
                Log.d(TAG, "Despesa adicionada com sucesso")
            }, {
                Log.d(TAG, "Erro ao adicionar despesa") //TODO
            })
        }
        finishActivity()
    }

    fun onIdLoaded(id: String?, setupValores: () -> Unit) {
        if (id != null) {
            load(id)
        } else{
            setupValores()
        }
    }

    private fun load(id: String) {
        DespesaRepository.getById(id, {
            mDespesa.postValue(it)
        }, {
            Log.d(TAG, "Erro ao resgatar despesa") //TODO
        })
    }
}