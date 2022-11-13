package dev.notrobots.androidstuff.widget

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import dev.notrobots.androidstuff.util.bindView
import kotlin.reflect.KClass

/**
 * Base boilerplate ViewHolder that supports ViewBindings.
 */
abstract class BindableViewHolder<T : ViewBinding> : BaseViewHolder {
    val binding: T

    constructor(type: KClass<T>, itemView: View) : super(itemView) {
        this.binding = bindView(type, itemView)
    }

    constructor(type: KClass<T>, parent: ViewGroup) : this(bindView(type, parent))

    constructor(binding: T) : super(binding.root) {
        this.binding = binding
    }
}