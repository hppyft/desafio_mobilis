package com.example.desafiomobilis.viewmodel

import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.repository.DespesaRepository

class CriarDespesaViewModel:CriarViewModel<Despesa>() {

    override fun getRepository()= DespesaRepository
}