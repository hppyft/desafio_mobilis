package com.example.desafiomobilis.repository

import com.example.desafiomobilis.model.Despesa
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

object DespesaRepository {

    val db = Firebase.firestore
    const val Collection = "despesas"

    fun add(despesa: Despesa, success: () -> Unit, failure: (Exception) -> Unit) {
        val id = db.collection(
            Collection
        ).document().id
        despesa.id = id
        db.collection(
            Collection
        )
            .document(id)
            .set(despesa)
            .addOnSuccessListener {
                success()
            }
            .addOnFailureListener {
                failure(it)
            }
    }

    fun update(despesa: Despesa, success: () -> Unit, failure: (Exception) -> Unit) {
        db.collection(
            Collection
        )
            .document(despesa.id!!)
            .set(despesa)
            .addOnSuccessListener {
                success()
            }
            .addOnFailureListener {
                failure(it)
            }
    }

    fun getAll(success: (List<Despesa>) -> Unit, failure: (Exception) -> Unit) {
        db.collection(
            Collection
        )
            .get()
            .addOnSuccessListener {
                success(it.toObjects(Despesa::class.java))
            }
            .addOnFailureListener {
                failure(it)
            }
    }

    fun getById(despesaId: String, success: (Despesa?) -> Unit, failure: (Exception) -> Unit) {
        db.collection(
            Collection
        )
            .document(despesaId)
            .get()
            .addOnSuccessListener {
                success(it.toObject(Despesa::class.java))
            }
            .addOnFailureListener {
                failure(it)
            }
    }
}