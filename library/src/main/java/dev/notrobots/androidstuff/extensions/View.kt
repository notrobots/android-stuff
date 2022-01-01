package dev.notrobots.androidstuff.extensions

import android.view.View
import androidx.viewbinding.ViewBinding
import dev.notrobots.androidstuff.util.viewBindings

/**
 * Sets this view's visibility to [View.INVISIBLE]
 */
fun View.hide() {
    visibility = View.INVISIBLE
}

/**
 * Sets this view's visibility to [View.VISIBLE]
 */
fun View.show() {
    visibility = View.VISIBLE
}

/**
 * Sets this view's visibility to [View.GONE]
 */
fun View.disable() {
    visibility = View.GONE
}

/**
 * Sets this view's visibility to [View.VISIBLE] or [View.INVISIBLE]
 */
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.INVISIBLE
}

/**
 * Sets this view's visibility to [View.INVISIBLE] or [View.VISIBLE]
 */
fun View.setHidden(hidden: Boolean) {
    visibility = if (hidden) View.INVISIBLE else View.VISIBLE
}

/**
 * Sets this view's visibility to [View.GONE] or [View.VISIBLE]
 */
fun View.setDisabled(disabled: Boolean = true) {
    visibility = if (disabled) View.GONE else View.VISIBLE
}

inline fun <reified T : ViewBinding> View.viewBindings(): Lazy<T> {
    return viewBindings(context)
}