package dev.notrobots.androidstuff.extensions

import android.app.Activity
import androidx.viewbinding.ViewBinding
import dev.notrobots.androidstuff.util.viewBindings

inline fun <reified T : ViewBinding> Activity.viewBindings(): Lazy<T> {
    return viewBindings(this)
}