package com.example.desafiomobilis.repository

import com.example.desafiomobilis.model.Despesa

object DespesaRepository : MFRepository<Despesa>() {

    override fun getCollection() = "despesas"

    override fun getClassToken() = Despesa::class.java
}