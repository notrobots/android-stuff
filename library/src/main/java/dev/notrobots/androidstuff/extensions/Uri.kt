package dev.notrobots.androidstuff.extensions

import android.net.Uri

fun Uri.hasQueryParameter(name: String): Boolean {
    return name in queryParameterNames
}

fun Uri.Builder.replaceQueryParameter(key: String, newValue: String) = apply{
    val oldUri = build()

    clearQuery()

    for (param in oldUri.queryParameterNames) {
        appendQueryParameter(param, if (param == key) newValue else oldUri.getQueryParameter(param))
    }
}