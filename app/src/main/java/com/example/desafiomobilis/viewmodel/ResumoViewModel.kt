package com.example.desafiomobilis.viewmodel

import androidx.lifecycle.*
import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.repository.DespesaRepository

class ResumoViewModel : ViewModel(), LifecycleObserver {

    companion object {
        const val ERROR_GET_LIST = 1
    }

    private val mDespesaList = MutableLiveData<List<Despesa>>()
    private val mError = MutableLiveData<Int>().apply { value = -1 }

    fun getDespesaList(): LiveData<List<Despesa>> = mDespesaList
    fun getError():LiveData<Int> = mError

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        load()
    }

    fun load() {
        DespesaRepository.getAll({
            mDespesaList.postValue(it)
        }, {
            mError.postValue(ERROR_GET_LIST)
        })
    }

    fun onCriarClicked(startActivity: () -> Unit) {
        startActivity()
    }

    fun onAlterarClicked(startActivity: () -> Unit) {
        startActivity()
    }
}