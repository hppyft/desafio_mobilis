package com.example.desafiomobilis.viewmodel

import com.example.desafiomobilis.model.Despesa
import com.example.desafiomobilis.repository.DespesaRepository

class ResumoDespesaViewModel : ResumoMFViewModel<Despesa>() {
    override fun getRepository() = DespesaRepository
}