package dev.notrobots.androidstuff.util

import android.os.Build
import dev.notrobots.androidstuff.BuildConfig
import dev.notrobots.androidstuff.extensions.toStringOrEmpty
import android.util.Log as AndroidLog

abstract class AbsLogger {
    /**
     * Tag used for the logs.
     */
    abstract var tag: String?

    abstract fun log(
        message: Any?,
        throwable: Throwable? = null,
        level: LogLevel,
        enabled: Boolean = true,
        debugOnly: Boolean = true,
    )

    open fun log(
        message: Any?,
        throwable: Throwable? = null,
        enabled: Boolean = true,
        debugOnly: Boolean = true,
    ) = log(message, throwable, LogLevel.Verbose, enabled, debugOnly)

    open fun loge(
        message: Any?,
        throwable: Throwable? = null,
        enabled: Boolean = true,
        debugOnly: Boolean = true,
    ) = log(message, throwable, LogLevel.Error, enabled, debugOnly)

    open fun logd(
        message: Any?,
        throwable: Throwable? = null,
        enabled: Boolean = true,
        debugOnly: Boolean = true,
    ) = log(message, throwable, LogLevel.Debug, enabled, debugOnly)

    open fun logi(
        message: Any?,
        throwable: Throwable? = null,
        enabled: Boolean = true,
        debugOnly: Boolean = true,
    ) = log(message, throwable, LogLevel.Info, enabled, debugOnly)

    open fun logw(
        message: Any?,
        throwable: Throwable? = null,
        enabled: Boolean = true,
        debugOnly: Boolean = true,
    ) = log(message, throwable, LogLevel.Warn, enabled, debugOnly)

    open fun logwtf(
        message: Any?,
        throwable: Throwable? = null,
        enabled: Boolean = true,
        debugOnly: Boolean = true,
    ) = log(message, throwable, LogLevel.Warn, enabled, debugOnly)
}

enum class LogLevel {
    Debug,
    Error,
    WTF,
    Info,
    Warn,
    Verbose
}

class Logger(tag: String?) : AbsLogger() {
    override var tag: String? = tag

    constructor(any: Any) : this(any::class.simpleName)

    override fun log(
        message: Any?,
        throwable: Throwable?,
        level: LogLevel,
        enabled: Boolean,
        debugOnly: Boolean,
    ) {
        log(message, throwable, tag, level, enabled, debugOnly)
    }

    companion object : AbsLogger() {
        override var tag: String? = null

        override fun log(
            message: Any?,
            throwable: Throwable?,
            level: LogLevel,
            enabled: Boolean,
            debugOnly: Boolean,
        ) {
            log(message, throwable, tag, level, enabled, debugOnly)
        }

        /**
         * Default implementation for both static and non-static.
         */
        private fun log(
            message: Any?,
            throwable: Throwable?,
            tag: String?,
            level: LogLevel,
            enabled: Boolean,
            debugOnly: Boolean,
        ) {
            val tag = tag ?: ""
            val message = message.toStringOrEmpty()

            if (!enabled) return

            if (debugOnly && !BuildConfig.DEBUG) return

            if (Build.VERSION.SDK_INT <= 25 && tag.length > 23) {
                loge("Tag length must be 23 or less, message will be ignored")
                return
            }

            when (level) {
                LogLevel.Debug -> AndroidLog.d(tag, message, throwable)
                LogLevel.Error -> AndroidLog.e(tag, message, throwable)
                LogLevel.Info -> AndroidLog.i(tag, message, throwable)
                LogLevel.Warn -> AndroidLog.w(tag, message, throwable)
                LogLevel.Verbose -> AndroidLog.v(tag, message, throwable)
                LogLevel.WTF -> AndroidLog.wtf(tag, message, throwable)
            }
        }
    }
}