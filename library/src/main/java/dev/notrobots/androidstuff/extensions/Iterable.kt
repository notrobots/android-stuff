package dev.notrobots.androidstuff.extensions

import dev.notrobots.androidstuff.util.error

fun <T> Iterable<T>.subList(vararg indexes: Int): List<T> {
    return indexes.map { elementAt(it) }
}

fun <T> Iterable<T>.subList(indexes: List<Int>): List<T> {
    return indexes.map { elementAt(it) }
}

fun <T> Iterable<T>.chunked(size: Int, count: (T) -> Int): List<List<T>> {
    val chunkList = mutableListOf<List<T>>()
    var chunk = mutableListOf<T>()
    var chunkSize = 0

    if (size <= 0 || count() == 0) {
        return listOf(toList())
    }

    for ((i, e) in withIndex()) {
        val c = count(e)

        if (c > size) {
            error("Element's size at position $i is greater than the max size of each chunk")
        }

        if (c + chunkSize > size) {
            chunkList.add(chunk)
            chunk = mutableListOf()
            chunkSize = 0
        }

        chunk.add(e)
        chunkSize += c
    }

    chunkList.add(chunk)

    return chunkList
}