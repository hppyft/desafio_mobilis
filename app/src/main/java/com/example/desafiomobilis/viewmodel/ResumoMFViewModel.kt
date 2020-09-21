package com.example.desafiomobilis.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.*
import com.example.desafiomobilis.model.MovimentacaoFinanceira
import com.example.desafiomobilis.repository.MFRepository

abstract class ResumoMFViewModel<T : MovimentacaoFinanceira> : ViewModel(), LifecycleObserver {

    abstract fun getRepository(): MFRepository<T>

    companion object {
        const val ERROR_GET_LIST = 1
    }

    protected val mList = MutableLiveData<List<T>>()
    protected val mError = MutableLiveData<Int>().apply { value = -1 }

    fun getList(): LiveData<List<T>> = mList
    fun getError(): LiveData<Int> = mError

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        load()
    }

    fun load() {
        getRepository().getAll({
            mList.postValue(it)
        }, {
            mError.postValue(ERROR_GET_LIST)
        })
    }

    fun onRemoverClicked(id: String) {
        getRepository().delete(id, {
            load()
        }, {
            Log.d(
                ContentValues.TAG,
                "Ocorreu erro ao deletar item de id $id"
            )
        })
    }

    fun onCriarClicked(startActivity: () -> Unit) {
        startActivity()
    }

    fun onAlterarClicked(startActivity: () -> Unit) {
        startActivity()
    }
}