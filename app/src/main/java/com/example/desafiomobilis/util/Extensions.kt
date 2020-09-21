package com.example.desafiomobilis.util

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*

fun AppCompatActivity.addFragmentTo(containerId: Int, fragment: Fragment, tag: String = "") {
    supportFragmentManager.beginTransaction().add(containerId, fragment, tag).commit()
}

fun <T> Fragment.navigate(activityClass: Class<T>, bundle: Bundle = Bundle()) {
    activity?.let {
        val intent = Intent(it, activityClass)
        intent.putBundle(bundle)
        startActivity(intent)
    }
}

fun Intent.putBundle(bundle: Bundle) {
    putExtra("com.example.desafiomobilis.util.extrabundle",bundle)
}

fun Intent.getBundle(): Bundle {
return extras?.get("com.example.desafiomobilis.util.extrabundle") as Bundle
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