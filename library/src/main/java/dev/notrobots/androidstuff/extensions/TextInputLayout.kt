package dev.notrobots.androidstuff.extensions

import android.text.TextWatcher
import androidx.annotation.StringRes
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputLayout

/**
 * Whether or not there are any errors.
 *
 * This will trigger all TextWatcher attached to this TextInputLayout's editText.
 */
val TextInputLayout.hasErrors: Boolean
    get() {
        editText?.let { it.text = it.text }
        return !error.isNullOrEmpty()
    }

/**
 * Adds to the TextView a [TextWatcher] that will clear the error when the text changes.
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setClearErrorOnType(): TextWatcher? {
    return editText?.addTextChangedListener {
        error = null
    }
}

/**
 * Sets the given [error] when the given [condition] is met, otherwise clears the error.
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setErrorWhen(error: String?, condition: (String, Int, Int, Int) -> Boolean): TextWatcher? {
    return editText?.addTextChangedListener(onTextChanged = { text, start, before, count ->
        if (condition((text ?: "").toString(), start, before, count)) {
            setError(error as Any?)
        } else {
            setError(null)
        }
    })
}

/**
 * Sets the given [error] when the given [condition] is met, otherwise clears the error.
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setErrorWhen(error: String?, condition: (String) -> Boolean): TextWatcher? {
    return editText?.addTextChangedListener(onTextChanged = { text, _, _, _ ->
        if (condition((text ?: "").toString())) {
            setError(error as Any?)
        } else {
            setError(null)
        }
    })
}

/**
 * Sets the given [error] when the given [condition] is met, otherwise clears the error.
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setErrorWhen(@StringRes error: Int, condition: (String, Int, Int, Int) -> Boolean): TextWatcher? {
    return setErrorWhen(context.resources.getString(error), condition)
}

/**
 * Sets the given [error] when the given [condition] is met, otherwise clears the error.
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setErrorWhen(@StringRes error: Int, condition: (String) -> Boolean): TextWatcher? {
    return setErrorWhen(context.resources.getString(error), condition)
}

/**
 * Sets the error returned by the [validator].
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setError(validator: (String, Int, Int, Int) -> Any?): TextWatcher? {
    return editText?.addTextChangedListener(onTextChanged = { text, start, before, count ->
        val error = validator((text ?: "").toString(), start, before, count)

        setError(error)
    })
}

/**
 * Sets the error returned by the [validator].
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setError(validator: (String) -> Any?): TextWatcher? {
    return editText?.addTextChangedListener(onTextChanged = { text, start, before, count ->
        val error = validator((text ?: "").toString())

        setError(error)
    })
}

/**
 * Sets the error of the first one of the [validators] that meets the given condition.
 *
 * To disable this, call [TextInputLayout.removeTextChangedListener] and pass the TextWatcher
 * returned by this method.
 */
fun TextInputLayout.setError(vararg validators: Pair<Any?, (text: String) -> Boolean>): TextWatcher? {
    return editText?.addTextChangedListener {
        val text = (it ?: "").toString()
        val first = validators.find { it.second(text) }

        setError(first?.first)
    }
}

/**
 * Sets an error message that will be displayed below our EditText. If the error is null,
 * the error message will be cleared.
 */
fun TextInputLayout.setError(error: Any?) {
    if (error == null) {
        setError(null)
    }

    if (getError() != error) {
        setError(
            when (error) {
                is Int -> context.resources.getString(error)
                is String -> error

                else -> error.toString()
            }
        )
    }
}

/**
 * Adds a TextWatcher to the list of those whose methods are called whenever
 * this TextInputLayout's EditText's text changes.
 */
fun TextInputLayout.addTextChangedListener(textWatcher: TextWatcher) {
    editText?.addTextChangedListener(textWatcher)
}

/**
 * Removes the specified TextWatcher from the list of those whose methods are called
 * whenever this TextInputLayout's EditText's text changes.
 */
fun TextInputLayout.removeTextChangedListener(textWatcher: TextWatcher) {
    editText?.removeTextChangedListener(textWatcher)
}