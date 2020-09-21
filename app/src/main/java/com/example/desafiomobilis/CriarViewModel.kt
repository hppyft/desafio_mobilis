package com.example.desafiomobilis

import androidx.lifecycle.ViewModel

class CriarViewModel: ViewModel() {

    fun onFinalizarClicked(
        data: String,
        valor: String,
        descricao: String,
        checkedRb: Int,
        finishActivity: () -> Unit
    ) {
       //todo validate
        finishActivity()
    }

    fun onIdLoaded(id: Long?) {
        if (id !=null){
            load(id)
        } else{

        }
    }

    private fun load(id: Long) {
        //todo
    }
}