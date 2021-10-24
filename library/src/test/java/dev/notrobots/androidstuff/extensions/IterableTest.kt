package dev.notrobots.androidstuff.extensions

import org.junit.Test

class IterableTest {
    @Test
    fun testChunked() {
        val items = listOf(
            Item(10),
            Item(30),   // Chunk 1
            Item(50),   // Chunk 2
            Item(40),   // Chunk 3
            Item(4),
            Item(5),
            Item(35),   // Chunk 4
            Item(25),
            Item(25),   // Chunk 5
            Item(25)    // Chunk 6
        )
        val chunked = items.chunked(50) { it.weight }

        assert(chunked.size == 6)
    }

    private data class Item(val weight: Int)
}