package dev.notrobots.androidstuff.widget

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredFunctions

abstract class BindableViewHolder<T : ViewBinding> : BaseViewHolder {
    val binding: T

    constructor(itemView: View, type: KClass<T>) : super(itemView) {
        this.binding = bind(super.itemView, type)
    }

    constructor(@LayoutRes layoutRes: Int, parent: ViewGroup, type: KClass<T>) : super(layoutRes, parent) {
        this.binding = bind(super.itemView, type)
    }

    private fun <T : ViewBinding> bind(view: View, type: KClass<T>): T {
        val bind = type.declaredFunctions.find {
            it.name == "bind" &&
            it.parameters.size == 1 &&
            it.parameters[0].type.classifier == View::class
        } ?: throw Exception("Cannot find method 'inflate(View)'")

        return bind.call(view) as T
    }
}