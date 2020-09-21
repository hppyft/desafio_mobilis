package com.example.desafiomobilis

import java.util.*

data class Usuario(val nome: String, val email: String)
data class Despesa(
    var id:Long? = null,
    var valor: Double,
    var descricao: String,
    var data: Date,
    var pago: Boolean,
    var anexo: String? = null
)

data class Receita(
    var valor: Double,
    var descricao: String,
    var data: Date,
    var recebido: Boolean,
    var anexo: String? = null
)