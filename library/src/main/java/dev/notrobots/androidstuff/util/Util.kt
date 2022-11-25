@file:JvmName("AndroidStuff")

package dev.notrobots.androidstuff.util

import android.graphics.Color
import java.util.concurrent.TimeUnit
import kotlin.reflect.KClass
import kotlin.reflect.jvm.isAccessible

//region Parsing

@JvmOverloads
inline fun <reified E : Enum<E>> parseEnum(value: CharSequence?, ignoreCase: Boolean = false): E {
    val values = E::class.java.enumConstants!!

    require(values.isNotEmpty()) {
        "Enum type ${E::class} has no values"
    }

    return values.find {
        it.name.equals(value.toString(), ignoreCase)
    } ?: throw Exception("Enum type ${E::class} has no value \"$value\"")
}

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

//endregion

//region Errors/Exceptions

inline fun <T : CharSequence> requireNotEmpty(value: T?, lazyMessage: () -> Any): T {
    if (value.isNullOrEmpty()) {
        val message = lazyMessage()
        throw Exception(message.toString())
    } else {
        return value
    }
}

inline fun <T : Iterable<T>> requireNotEmpty(value: T?, lazyMessage: () -> Any): T {
    if (value == null || value.count() == 0) {
        val message = lazyMessage()
        throw Exception(message.toString())
    } else {
        return value
    }
}

inline fun <T> requireNotEmpty(value: Array<T>?, lazyMessage: () -> Any): Array<T> {
    if (value == null || value.count() == 0) {
        val message = lazyMessage()
        throw Exception(message.toString())
    } else {
        return value
    }
}

//endregion

fun <T, V> swap(a: T, b: T, get: (T) -> V, set: (T, V) -> Unit) {
    val c = get(a)

    set(a, get(b))
    set(b, c)
}

inline fun <reified T> swap(a: T, b: T, field: String) {
    val field = T::class.java.declaredFields.find { it.name == field }

    if (field != null) {
        val c = field.get(a)

        field.set(a, field.get(b))
        field.set(b, c)
    } else {
        throw Exception("Type ${T::class} has no field named '$field'")
    }
}

//region Time

/**
 * Returns the current time in milliseconds
 */
fun now(): Long {
    return System.currentTimeMillis()
}

/**
 * Returns the current time in the given [timeUnit]
 */
fun now(timeUnit: TimeUnit): Long {
    val now = now()

    return when (timeUnit) {
        TimeUnit.NANOSECONDS -> TimeUnit.MILLISECONDS.toNanos(now)
        TimeUnit.MICROSECONDS -> TimeUnit.MILLISECONDS.toMicros(now)
        TimeUnit.MILLISECONDS -> TimeUnit.MILLISECONDS.toMillis(now)
        TimeUnit.SECONDS -> TimeUnit.MILLISECONDS.toSeconds(now)
        TimeUnit.MINUTES -> TimeUnit.MILLISECONDS.toMinutes(now)
        TimeUnit.HOURS -> TimeUnit.MILLISECONDS.toHours(now)
        TimeUnit.DAYS -> TimeUnit.MILLISECONDS.toDays(now)
    }
}

//endregion

/**
 * Creates a new instance of the Lazy that uses the specified type.
 *
 * The type must have an empty constructor.
 */
inline fun <reified T : Any> lazyType(crossinline initializer: T.() -> Unit = {}): Lazy<T> {
    return lazyType(T::class, initializer)
}

/**
 * Creates a new instance of the Lazy that uses the specified [type].
 *
 * The type must have an empty constructor.
 */
inline fun <reified T : Any> lazyType(type: KClass<T>, crossinline initializer: T.() -> Unit = {}): Lazy<T> {
    val emptyConstructor = type.constructors.find {
        it.parameters.isEmpty()
    } ?: throw Exception("Type $type has no empty constructor")

    emptyConstructor.isAccessible = true

    return lazy {
        emptyConstructor.call().apply(initializer)
    }
}