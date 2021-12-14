package dev.notrobots.androidstuff.util

import android.app.Activity
import androidx.viewbinding.ViewBinding
import dev.notrobots.androidstuff.extensions.bindView
import java.util.concurrent.TimeUnit

//region Types

inline fun <reified E : Enum<E>> parseEnum(value: CharSequence?, ignoreCase: Boolean = false): E? {
    val values = E::class.java.enumConstants

    return values.find { it.name.equals(value.toString(), ignoreCase) }
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

inline fun <reified T : ViewBinding> viewBindings(activity: Activity): Lazy<T> {
    return lazy {
        activity.bindView()
    }
}