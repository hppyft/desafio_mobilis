package com.example.desafiomobilis.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafiomobilis.model.MovimentacaoFinanceira
import com.example.desafiomobilis.repository.MFRepository
import com.example.desafiomobilis.util.clean

abstract class CriarViewModel<T:MovimentacaoFinanceira> : ViewModel() {

    abstract fun getRepository():MFRepository<T>

    private val mMF = MutableLiveData<T?>()

    fun getMF():LiveData<T?> = mMF

    fun onFinalizarClicked(
        mf: T,
        finishActivity: () -> Unit
    ) {
        mf.data?.clean()
        if (mf.id != null){
            getRepository().update(mf, {
                Log.d(TAG, "Despesa atualizada com sucesso")
            }, {
                Log.d(TAG, "Erro ao adicionar despesa") //TODO
            })
        } else{
            getRepository().add(mf, {
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
        getRepository().getById(id, {
            mMF.postValue(it)
        }, {
            Log.d(TAG, "Erro ao resgatar despesa") //TODO
        })
    }
}