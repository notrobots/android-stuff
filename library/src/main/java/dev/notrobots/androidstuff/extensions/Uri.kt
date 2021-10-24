package dev.notrobots.androidstuff.extensions

import android.net.Uri

operator fun Uri.get(name: String): String? {
    return getQueryParameter(name)
}