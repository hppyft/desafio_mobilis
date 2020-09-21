package com.example.desafiomobilis.viewmodel

import com.example.desafiomobilis.model.Receita
import com.example.desafiomobilis.repository.ReceitaRepository

class ResumoReceitaViewModel : ResumoMFViewModel<Receita>() {

    override fun getRepository() = ReceitaRepository
}