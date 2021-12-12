package dev.notrobots.androidstuff.util

import android.graphics.Color

/**
 * Parses the given [string] into a color int
 */
fun parseColor(string: String): Int {
    val rgb = Regex("^rgb\\((\\d{1,3}),(\\d{1,3}),(\\d{1,3})\\)$")
    val hex = Regex("^#[A-F0-9]{6}$", RegexOption.IGNORE_CASE)
    val zeroX = Regex("^0x[A-F0-9]{6}$", RegexOption.IGNORE_CASE)

    return when {
        string.matches(hex) -> Color.parseColor(string)
        string.matches(zeroX) -> Color.parseColor(string.replace("0x", "#"))
        string.matches(rgb) -> {
            val match = rgb.matchEntire(string)!!

            Color.rgb(
                match.groupValues[1].toInt(),
                match.groupValues[2].toInt(),
                match.groupValues[3].toInt()
            )
        }
        else -> Color.WHITE
    }
}

/**
 * Parses the given [color] int into an HEX string
 */
fun parseHEXColor(color: Int): String {
    return String.format("#%06X", 0xFFFFFF and color)
}