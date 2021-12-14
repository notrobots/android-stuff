package dev.notrobots.androidstuff.extensions

import android.app.Activity
import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import kotlin.reflect.full.declaredFunctions

inline fun <reified T : ViewBinding> Activity.bindView(): T {
    val inflate = T::class.declaredFunctions.find {
        it.name == "inflate" &&
        it.parameters.size == 1 &&
        it.parameters[0].type.classifier == LayoutInflater::class
    } ?: throw Exception("Cannot find method 'inflate(LayoutInflate)'")

    return inflate.call(layoutInflater) as T
}

inline fun <reified T : ViewBinding> Activity.viewBindings(): Lazy<T> {
    return lazy { bindView() }
}