package dev.notrobots.androidstuff.extensions

fun Any?.toStringOrEmpty(): String {
    return this?.toString() ?: ""
}