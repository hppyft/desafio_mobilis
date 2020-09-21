package com.example.desafiomobilis

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatActivity.addFragmentTo(containerId: Int, fragment: Fragment, tag: String = "") {
    supportFragmentManager.beginTransaction().add(containerId, fragment, tag).commit()
}

fun <T> Fragment.navigate(activityClass: Class<T>) {
    activity?.let {
        val intent = Intent(it, activityClass)
        startActivity(intent)
    }
}

fun Date.toFormattedString(): String {
    val format = SimpleDateFormat("dd/MM/yyyy")
    return format.format(this)
}

fun Double.toReal(): String {
    return "R$ $this"
}

fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSafeClick(it)
    }
    setOnClickListener(safeClickListener)
}