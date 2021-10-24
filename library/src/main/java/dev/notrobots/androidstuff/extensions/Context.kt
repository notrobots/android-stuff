package dev.notrobots.androidstuff.extensions

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.print.PrintAttributes
import android.print.PrintManager
import android.util.TypedValue
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.print.PrintHelper
import dev.notrobots.androidstuff.util.now
import kotlin.reflect.KClass

fun Context.makeToast(content: Any?, duration: Int = Toast.LENGTH_SHORT): Toast {
    return Toast.makeText(this, content.toString(), duration).also {
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