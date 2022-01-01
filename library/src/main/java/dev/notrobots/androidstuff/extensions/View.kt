package dev.notrobots.androidstuff.extensions

import android.view.View
import androidx.viewbinding.ViewBinding
import dev.notrobots.androidstuff.util.viewBindings

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

inline fun <reified T : ViewBinding> View.viewBindings(): Lazy<T> {
    return viewBindings(context)
}