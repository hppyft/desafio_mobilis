package com.example.desafiomobilis.repository

import com.example.desafiomobilis.model.Receita

object ReceitaRepository : MFRepository<Receita>() {

    override fun getCollection() = "receitas"

    override fun getClassToken() = Receita::class.java
}