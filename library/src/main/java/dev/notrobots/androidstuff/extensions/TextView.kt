package dev.notrobots.androidstuff.extensions

import android.text.TextWatcher
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener

val TextView.hasErrors
    get() = !error.isNullOrEmpty()

/**
 * Adds to the TextView a [TextWatcher] that will clear the error when the text changes
 *
 * To disable this call [TextView.removeTextChangedListener] and pass the TextWatcher
 * returned by this method
 */
fun TextView.setClearErrorOnType(): TextWatcher {
    return addTextChangedListener {
        error = null
    }
}

/**
 * Sets the given [error] when the given [condition] is true
 *
 * To disable this call [TextView.removeTextChangedListener] and pass the TextWatcher
 * returned by this method
 */
inline fun TextView.setErrorWhen(error: String?, crossinline condition: (CharSequence?, Int, Int, Int) -> Boolean): TextWatcher {
    return addTextChangedListener(onTextChanged = { text, start, before, count ->
        if (condition(text, start, before, count)) {
            setError(error)
        }
    })
}

/**
 * Sets the given [error] when the given [condition] is true
 *
 * To disable this call [TextView.removeTextChangedListener] and pass the TextWatcher
 * returned by this method
 */
inline fun TextView.setErrorWhen(error: String?, crossinline condition: (CharSequence?) -> Boolean): TextWatcher {
    return addTextChangedListener(onTextChanged = { text, _, _, _ ->
        if (condition(text)) {
            setError(error)
        }
    })
}

/**
 * Sets the given [error] when the given [condition] is true
 *
 * To disable this call [TextView.removeTextChangedListener] and pass the TextWatcher
 * returned by this method
 */
inline fun TextView.setErrorWhen(@StringRes error: Int, crossinline condition: (CharSequence?, Int, Int, Int) -> Boolean): TextWatcher {
    return setErrorWhen(context.resources.getString(error), condition)
}

/**
 * Sets the given [error] when the given [condition] is true
 *
 * To disable this call [TextView.removeTextChangedListener] and pass the TextWatcher
 * returned by this method
 */
inline fun TextView.setErrorWhen(@StringRes error: Int, crossinline condition: (CharSequence?) -> Boolean): TextWatcher {
    return setErrorWhen(context.resources.getString(error), condition)
}