package dev.notrobots.androidstuff.extensions

import android.view.View

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}