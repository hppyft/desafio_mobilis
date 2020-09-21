package com.example.desafiomobilis.viewmodel
//
//import androidx.lifecycle.*
//import com.example.desafiomobilis.model.Despesa
//import com.example.desafiomobilis.model.Receita
//import com.example.desafiomobilis.repository.DespesaRepository
//import com.example.desafiomobilis.repository.ReceitaRepository
//import com.github.mikephil.charting.data.*
//
//class GraficoViewModel : ViewModel(), LifecycleObserver {
//
//    companion object {
//        const val ERROR_GET_DESPESA_LIST = 1
//        const val ERROR_GET_RECEITA_LIST = 2
//    }
//
//    private val mData = MutableLiveData<PieData?>()
//    private val mError = MutableLiveData<Int>().apply { value = -1 }
//
//    fun getData(): LiveData<PieData?> = mData
//    fun getError(): LiveData<Int> = mError
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
//    private fun onResume() {
//        load()
//    }
//
//    fun load(){
//        loadDespesa()
//    }
//
//    private fun loadDespesa() {
//        DespesaRepository.getAll({
//            loadReceita(it)
//        }, {
//            mError.postValue(ERROR_GET_DESPESA_LIST)
//        })
//    }
//
//    private fun loadReceita(despesas: List<Despesa>) {
//        ReceitaRepository.getAll({
//            calculate(despesas, it)
//        }, {
//            mError.postValue(ERROR_GET_RECEITA_LIST)
//        })
//    }
//
//    private fun calculate(despesas: List<Despesa>, receitas: List<Receita>) {
//        val entries = mutableListOf<PieEntry>()
////        entries.add()
//        val dataSet = PieDataSet(entries, "Saldos")
//        val data = PieData(dataSet)
//        mData.postValue(data)
//    }
//}