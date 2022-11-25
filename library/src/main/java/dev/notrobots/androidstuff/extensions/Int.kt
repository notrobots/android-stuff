package dev.notrobots.androidstuff.extensions

fun Int.toHex(): String {
    return "%x".format(this)
}

fun Int.to0x(): String {
    return "0x" + toHex()
}