@file:JvmName("ViewBindingsUtil")
@file:Suppress("unused")

package dev.notrobots.androidstuff.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredFunctions

/**
 * Creates a ViewBinding of the specified type using the given [context].
 */
inline fun <reified T : ViewBinding> bindView(context: Context): T {
    return bindView(T::class, context)
}

/**
 * Creates a ViewBinding of the specified [type] using the given [context].
 */
fun <T : ViewBinding> bindView(type: KClass<T>, context: Context): T {
    return _ViewBinding_inflate(type, context)
}

/**
 * Creates a ViewBinding of the specified type using the given [activity]'s layoutInflater.
 */
inline fun <reified T : ViewBinding> bindView(activity: Activity): T {
    return bindView(T::class, activity)
}

/**
 * Creates a ViewBinding of the specified [type] using the given [activity]'s layoutInflater.
 */
fun <T : ViewBinding> bindView(type: KClass<T>, activity: Activity): T {
    return _ViewBinding_inflate(type, activity.layoutInflater)
}

/**
 * Creates a ViewBinding of the specified type using the given [layoutInflater]
 */
inline fun <reified T : ViewBinding> bindView(layoutInflater: LayoutInflater): T {
    return bindView(T::class, layoutInflater)
}

/**
 * Creates a ViewBinding of the specified [type] using the given [layoutInflater].
 */
fun <T : ViewBinding> bindView(
    type: KClass<T>,
    layoutInflater: LayoutInflater
): T {
    return _ViewBinding_inflate(type, layoutInflater)
}

/**
 * Creates a ViewBinding of the specified type using the given [parent].
 */
@JvmOverloads
inline fun <reified T : ViewBinding> bindView(
    parent: ViewGroup,
    attachToRoot: Boolean = false
): T {
    return bindView(T::class, parent, attachToRoot)
}

/**
 * Creates a ViewBinding of the specified [type] using the given [parent].
 */
@JvmOverloads
fun <T : ViewBinding> bindView(
    type: KClass<T>,
    parent: ViewGroup,
    attachToRoot: Boolean = false
): T {
    return _ViewBinding_inflate(type, parent, attachToRoot)
}

/**
 * Creates a ViewBinding of the specified type using the given [layoutInflater] and [parent].
 */
@JvmOverloads
inline fun <reified T : ViewBinding> bindView(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    attachToRoot: Boolean = false
): T {
    return bindView(T::class, layoutInflater, parent, attachToRoot)
}

/**
 * Creates a ViewBinding of the specified type using the given [layoutInflater] and [parent].
 */
@JvmOverloads
fun <T : ViewBinding> bindView(
    type: KClass<T>,
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    attachToRoot: Boolean = false
): T {
    return _ViewBinding_inflate(type, layoutInflater, parent, attachToRoot)
}

/**
 * Creates a ViewBinding of the specified type using the given [view].
 */
inline fun <reified T : ViewBinding> bindView(view: View): T {
    return bindView(T::class, view)
}

/**
 * Creates a ViewBinding of the specified [type] using the given [view].
 */
fun <T : ViewBinding> bindView(type: KClass<T>, view: View): T {
    return _ViewBinding_bind(type, view)
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified type.
 */
@JvmOverloads
inline fun <reified T : ViewBinding> viewBindings(
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    attachToRoot: Boolean = false
): Lazy<T> {
    return lazy {
        bindView(layoutInflater, parent, attachToRoot)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified [type].
 */
@JvmOverloads
fun <T : ViewBinding> viewBindings(
    type: KClass<T>,
    layoutInflater: LayoutInflater,
    parent: ViewGroup,
    attachToRoot: Boolean = false
): Lazy<T> {
    return lazy {
        bindView(type, layoutInflater, parent, attachToRoot)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified type.
 */
@JvmOverloads
inline fun <reified T : ViewBinding> viewBindings(
    parent: ViewGroup,
    attachToRoot: Boolean = false
): Lazy<T> {
    return lazy {
        bindView(parent, attachToRoot)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified [type].
 */
@JvmOverloads
fun <T : ViewBinding> viewBindings(
    type: KClass<T>,
    parent: ViewGroup,
    attachToRoot: Boolean = false
): Lazy<T> {
    return lazy {
        bindView(type, parent, attachToRoot)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified type.
 */
inline fun <reified T : ViewBinding> viewBindings(layoutInflater: LayoutInflater): Lazy<T> {
    return lazy {
        bindView(layoutInflater)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified [type].
 */
fun <T : ViewBinding> viewBindings(type: KClass<T>, layoutInflater: LayoutInflater): Lazy<T> {
    return lazy {
        bindView(type, layoutInflater)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified type.
 */
inline fun <reified T : ViewBinding> viewBindings(activity: Activity): Lazy<T> {
    return lazy {
        bindView(activity)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified [type].
 */
fun <T : ViewBinding> viewBindings(type: KClass<T>, activity: Activity): Lazy<T> {
    return lazy {
        bindView(type, activity)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified type.
 */
inline fun <reified T : ViewBinding> viewBindings(context: Context): Lazy<T> {
    return lazy {
        bindView(context)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified type.
 */
fun <T : ViewBinding> viewBindings(type: KClass<T>, context: Context): Lazy<T> {
    return lazy {
        bindView(type, context)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified type.
 */
inline fun <reified T : ViewBinding> viewBindings(view: View): Lazy<T> {
    return lazy {
        bindView(view)
    }
}

/**
 * Returns a Lazy delegate to access the ViewBinding of the specified [type].
 */
fun <T : ViewBinding> viewBindings(type: KClass<T>, view: View): Lazy<T> {
    return lazy {
        bindView(type, view)
    }
}

/**
 * Calls the given ViewBinding's `bind(View)` method and returns the ViewBinding.
 *
 * This is meant for internal use only. See [bindView].
 */
@Suppress("UNCHECKED_CAST")
internal fun <T : ViewBinding> _ViewBinding_bind(
    type: KClass<T>,
    view: View
): T {
    val bind = type.declaredFunctions.find {
        it.name == "bind" &&
                it.parameters.size == 1 &&
                it.parameters[0].type.classifier == View::class
    } ?: throw Exception("Cannot find method '$type.bind(View)'")

    return bind.call(view) as T
}

/**
 * Calls the given ViewBinding's `inflate(LayoutInflater, ViewGroup, Boolean)` method and returns the ViewBinding.
 *
 * This is meant for internal use only. See [bindView].
 */
@JvmOverloads
internal fun <T : ViewBinding> _ViewBinding_inflate(
    type: KClass<T>,
    parent: ViewGroup,
    attachToRoot: Boolean = false
): T {
    val inflater = LayoutInflater.from(parent.context)

    return _ViewBinding_inflate(type, inflater, parent, attachToRoot)
}

/**
 * Calls the given ViewBinding's `inflate(LayoutInflater)` method and returns the ViewBinding.
 *
 * This is meant for internal use only. See [bindView].
 */
internal fun <T : ViewBinding> _ViewBinding_inflate(
    type: KClass<T>,
    context: Context
): T {
    val inflater = LayoutInflater.from(context)

    return _ViewBinding_inflate(type, inflater)
}

/**
 * Calls the given ViewBinding's `inflate(LayoutInflater)` method and returns the ViewBinding.
 *
 * This is meant for internal use only. See [bindView].
 */
@Suppress("UNCHECKED_CAST")
internal fun <T : ViewBinding> _ViewBinding_inflate(
    type: KClass<T>,
    inflater: LayoutInflater
): T {
    val inflate = type.declaredFunctions.find {
        it.name == "inflate" &&
                it.parameters.size == 1 &&
                it.parameters[0].type.classifier == LayoutInflater::class
    } ?: throw Exception("Cannot find method '$type.inflate(LayoutInflater)'")

    return inflate.call(inflater) as T
}

/**
 * Calls the given ViewBinding's `inflate(LayoutInflater, ViewGroup, Boolean)` method and returns the ViewBinding.
 *
 * This is meant for internal use only. See [bindView].
 */
@Suppress("UNCHECKED_CAST")
@JvmOverloads
internal fun <T : ViewBinding> _ViewBinding_inflate(
    type: KClass<T>,
    inflater: LayoutInflater,
    parent: ViewGroup,
    attachToParent: Boolean = false
): T {
    val inflate = type.declaredFunctions.find {
        it.name == "inflate" &&
                it.parameters.size == 3 &&
                it.parameters[0].type.classifier == LayoutInflater::class &&
                it.parameters[1].type.classifier == ViewGroup::class &&
                it.parameters[2].type.classifier == Boolean::class
    } ?: throw Exception("Cannot find method '$type.inflate(LayoutInflater, ViewGroup, Boolean)'")

    return inflate.call(inflater, parent, attachToParent) as T
}