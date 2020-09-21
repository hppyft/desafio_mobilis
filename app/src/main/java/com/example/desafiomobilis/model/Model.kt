package com.example.desafiomobilis.model

import java.util.*

data class Usuario(val nome: String, val email: String)

abstract class MovimentacaoFinanceira() {
    var id: String? = null
    var valor: Double? = null
    var descricao: String? = null
    var data: Date? = null
    var efetuado: Boolean? = null
    var anexo: String? = null
}

class Despesa : MovimentacaoFinanceira()

class Receita : MovimentacaoFinanceira()