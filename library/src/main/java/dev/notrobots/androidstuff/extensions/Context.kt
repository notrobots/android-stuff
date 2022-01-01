package dev.notrobots.androidstuff.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.print.PrintAttributes
import android.print.PrintManager
import android.util.TypedValue
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.print.PrintHelper
import com.google.android.material.snackbar.Snackbar
import dev.notrobots.androidstuff.util.now
import kotlin.reflect.KClass

fun Context.makeSnackBar(
    content: Any?,
    parent: View,
    duration: Int = Snackbar.LENGTH_SHORT,
    action: Any? = null,
    actionCallback: (View, Snackbar) -> Unit = { _, _ -> }
): Snackbar {
    return Snackbar.make(
        this,
        parent,
        resolveString(content, false),
        duration
    ).apply {
        if (action != null) {
            val actionName = resolveString(action, false)

            setAction(actionName) {
                actionCallback(it, this)
            }
        }

        show()
    }
}

fun Context.makeToast(content: Any?, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(
        this,
        resolveString(content, false),
        duration
    ).also {
        it.show()
    }
}

fun Context.copyToClipboard(content: Any?) {
    val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText(null, content.toString())

    clipboardManager.setPrimaryClip(clip)
}

fun Context.resolveColorAttribute(id: Int): Int {
    return TypedValue().run {
        theme.resolveAttribute(id, this, true)
        data
    }
}

//region Print utils

fun Context.printImage(
    bitmap: Bitmap,
    jobName: String = "${now() / 1000}",
    scaleMode: Int = PrintHelper.SCALE_MODE_FIT
) {
    val printer = PrintHelper(this)

    printer.scaleMode = scaleMode
    printer.printBitmap(jobName, bitmap)
}

fun Context.printHTML(
    html: String,
    jobName: String = "${now() / 1000}",
    attributes: PrintAttributes = PrintAttributes.Builder().build()
) {
    val webView = WebView(this)

    webView.webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest) = false

        override fun onPageFinished(view: WebView, url: String) {
            val printManager = getSystemService(Context.PRINT_SERVICE) as? PrintManager
            val printAdapter = view.createPrintDocumentAdapter(jobName)

            printManager!!.print(
                jobName,
                printAdapter,
                attributes
            )
        }
    }
    webView.loadDataWithBaseURL(null, html, "text/HTML", "UTF-8", null)
}

//endregion

fun Context.startActivity(activityClass: KClass<*>, factory: Intent.() -> Unit = {}) {
    startActivity(activityClass.java, factory)
}

fun Context.startActivity(activityClass: Class<*>, factory: Intent.() -> Unit = {}) {
    Intent(this, activityClass).apply(factory).also {
        startActivity(it)
    }
}

//region Resolve utils

fun Context.resolveString(value: Any?, replaceNull: Boolean = true, vararg args: Any?): String {
    return String.format(resolveString(value, replaceNull), *args)
}

fun Context.resolveString(value: Any?, replaceNull: Boolean = true): String {
    if (value == null) {
        return if (replaceNull) "" else "null"
    }

    return when (value) {
        is String -> value
        is Int -> resources.getString(value)

        else -> value.toString()
    }
}

fun Context.resolveDrawable(value: Any?): Drawable? {
    if (value == null) {
        return null
    }

    return when (value) {
        is Drawable -> value
        is Int -> ContextCompat.getDrawable(this, value)

        else -> throw Exception("Unknown type")
    }
}

fun Context.resolveColor(value: Any?): Int {
    if (value == null) {
        return Color.WHITE
    }

    return when (value) {
        is ColorDrawable -> value.color
        is Int -> ContextCompat.getColor(this, value)
        is Color -> {
            if (Build.VERSION.SDK_INT >= 26) {
                value.toArgb()
            } else {
                throw Exception("Call requires API level 26")
            }
        }

        else -> throw Exception("Unknown type")
    }
}

//endregion