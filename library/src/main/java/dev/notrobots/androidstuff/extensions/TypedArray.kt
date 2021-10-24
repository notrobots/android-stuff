package dev.notrobots.androidstuff.extensions

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

fun TypedArray.getTextList(@StyleableRes index: Int): List<String>? {
    return getTextArray(index)?.toList()?.map { it.toString() }
}