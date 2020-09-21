package com.example.desafiomobilis.repository

import com.example.desafiomobilis.model.MovimentacaoFinanceira
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception

abstract class MFRepository<T : MovimentacaoFinanceira> {
    protected val db = Firebase.firestore

    abstract fun getCollection(): String
    abstract fun getClassToken(): Class<T>

    fun add(t: T, success: () -> Unit, failure: (Exception) -> Unit) {
        val id = db.collection(
            getCollection()
        ).document().id
        t.id = id

        db.collection(
            getCollection()
        )
            .document(id)
            .set(t)
            .addOnSuccessListener {
                success()
            }
            .addOnFailureListener(failure)
    }

    fun update(t: T, success: () -> Unit, failure: (Exception) -> Unit) {
        DespesaRepository.db.collection(
            getCollection()
        )
            .document(t.id!!)
            .set(t)
            .addOnSuccessListener {
                success()
            }
            .addOnFailureListener(failure)
    }

    fun getAll(success: (List<T>) -> Unit, failure: (Exception) -> Unit) {
        DespesaRepository.db.collection(
            getCollection()
        )
            .orderBy("data", Query.Direction.ASCENDING                                     ) //TODO
            .get()
            .addOnSuccessListener {
                success(it.toObjects(getClassToken()))
            }
            .addOnFailureListener(failure)
    }

    fun getById(id: String, success: (T?) -> Unit, failure: (Exception) -> Unit) {
        DespesaRepository.db.collection(
            getCollection()
        )
            .document(id)
            .get()
            .addOnSuccessListener {
                success(it.toObject(getClassToken()))
            }
            .addOnFailureListener(failure)
    }

    fun delete(id: String, success: () -> Unit, failure: (Exception) -> Unit) {
        DespesaRepository.db.collection(getCollection())
            .document(id)
            .delete()
            .addOnSuccessListener {
                success()
            }
            .addOnFailureListener(failure)
    }

}