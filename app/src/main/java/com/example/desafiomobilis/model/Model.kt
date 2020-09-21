package com.example.desafiomobilis.model

import java.util.*

data class Usuario(val nome: String, val email: String)
class Despesa() {
    var id: String? = null
    var valor: Double? = null
    var descricao: String? = null
    var data: Date? = null
    var pago: Boolean? = null
    var anexo: String? = null
}

data class Receita(
    var valor: Double,
    var descricao: String,
    var data: Date,
    var recebido: Boolean,
    var anexo: String? = null
)