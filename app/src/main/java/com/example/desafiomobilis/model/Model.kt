package com.example.desafiomobilis.model

import java.util.*

abstract class MovimentacaoFinanceira() {
    var id: String? = null
    var valor: Double? = null
    var descricao: String? = null
    var data: Date? = null
    var efetuado: Boolean? = null

    abstract fun getEfetuadoString(): String
    abstract fun getNaoEfetuadoString(): String
}

class Despesa : MovimentacaoFinanceira() {
    override fun getEfetuadoString() = "Pago"
    override fun getNaoEfetuadoString() = "Não Pago"
}

class Receita : MovimentacaoFinanceira() {
    override fun getEfetuadoString() = "Recebido"
    override fun getNaoEfetuadoString() = "Não Recebido"
}