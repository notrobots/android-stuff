package dev.notrobots.androidstuff.extensions

fun <T> Map<T, *>.slice(keys: Iterable<T>) = filterKeys { it in keys }

fun <K, V> Map<K, V>.find(predicate: (K, V) -> Boolean): Map.Entry<K, V>? {
    for (entry in entries) {
        if (predicate(entry.key, entry.value)) {
            return entry
        }
    }

    return null
}

operator fun <K, V> Map.Entry<K, V>.component1(): K {
    return this.key
}

operator fun <K, V> Map.Entry<K, V>.component2(): V {
    return this.value
}