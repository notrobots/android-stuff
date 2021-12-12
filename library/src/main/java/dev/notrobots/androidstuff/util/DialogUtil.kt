package dev.notrobots.androidstuff.util

import android.app.Dialog
import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dev.notrobots.androidstuff.extensions.idString

/**
 * Shows a dialog with the given [title] and [message]
 */
fun showInfo(
    context: Context,
    title: Any?,
    message: Any? = null,
    positiveButton: Any? = "Ok",
    positiveCallback: () -> Unit = {}
): Dialog {
    return MaterialAlertDialogBuilder(context)
        .setTitle(context.idString(title))
        .setMessage(context.idString(message))
        .setPositiveButton(context.idString(positiveButton)) { _, _ -> positiveCallback() }
        .create()
        .apply {
            show()
        }
}

/**
 * Shows a dialog with the given [title], [message] and two choices
 */
fun showChoice(
    context: Context,
    title: Any?,
    message: Any? = null,
    positiveButton: Any? = "Ok",
    positiveCallback: () -> Unit = {},
    negativeButton: Any? = "Cancel",
    negativeCallback: () -> Unit = {},
): Dialog {
    return MaterialAlertDialogBuilder(context)
        .setTitle(context.idString(title))
        .setMessage(context.idString(message))
        .setPositiveButton(context.idString(positiveButton)) { _, _ -> positiveCallback() }
        .setNegativeButton(context.idString(negativeButton)) { _, _ -> negativeCallback() }
        .create()
        .apply {
            show()
        }
}