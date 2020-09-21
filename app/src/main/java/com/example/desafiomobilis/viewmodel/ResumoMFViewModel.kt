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

    private val mList = MutableLiveData<List<T>>()
    private val mError = MutableLiveData<Int>().apply { value = -1 }
//    private val mChartData = MutableLiveData<PieData?>()

    fun getList(): LiveData<List<T>> = mList
    fun getError(): LiveData<Int> = mError
//    fun getChartData(): LiveData<PieData?> = mChartData

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private fun onResume() {
        load()
    }

    fun load() {
        loadList()
    }

//    private fun loadChart(it: List<T>) {
//        val entries = mutableListOf<PieEntry>()
//        var totalEfetuados: Double = 0.0
//        it.filter { it.efetuado != null && it.efetuado!! }.forEach {
//            totalEfetuados += it.valor!!
//        }
//        entries.add(PieEntry(totalEfetuados.toFloat(), it[0].getEfetuadoString()))
//        var totalNaoEfetuados: Double = 0.0
//        it.filter { it.efetuado != null && !it.efetuado!! }.forEach {
//            totalNaoEfetuados += it.valor!!
//        }
//        entries.add(PieEntry(totalNaoEfetuados.toFloat(), it[0].getNaoEfetuadoString()))
//        val dataSet = PieDataSet(entries, "Saldo")
//        val data = PieData(dataSet)
//        mChartData.postValue(data)
//    }

    private fun loadList() {
        getRepository().getAll({
            mList.postValue(it)
//            loadChart(it)
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