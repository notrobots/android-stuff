package dev.notrobots.androidstuff.util

import android.app.Activity
import android.util.Log as AndroidLog

abstract class AbsLogger {
    /**
     * Whether or not the logging is enabled for this logger.
     */
    abstract var isLoggingEnabled: Boolean

    /**
     * Tag used for the logs.
     */
    abstract var tag: Any?

    abstract fun log(message: Any?, tag: Any?, priority: LogPriority)

    abstract fun log(message: Any?, priority: LogPriority)

    open fun loge(message: Any?) = log(message, LogPriority.Error)

    open fun logd(message: Any?) = log(message, LogPriority.Debug)

    open fun logi(message: Any?) = log(message, LogPriority.Info)

    open fun log(message: Any?) = log(message, LogPriority.Verbose)

    open fun logw(message: Any?) = log(message, LogPriority.Warn)

    open fun loga(message: Any?) = log(message, LogPriority.Assert)

    open fun loge(exception: Exception, full: Boolean = true) {
        log(if (full) exception.stackTraceToString() else exception.message, LogPriority.Error)
    }
}

enum class LogPriority(val value: Int) {
    Assert(AndroidLog.ASSERT),
    Debug(AndroidLog.DEBUG),
    Error(AndroidLog.ERROR),
    Info(AndroidLog.INFO),
    Warn(AndroidLog.WARN),
    Verbose(AndroidLog.VERBOSE)
}

class Logger(tag: Any?) : AbsLogger() {
    override var tag: Any? = tag
    override var isLoggingEnabled: Boolean = true

    constructor(activity: Activity) : this(activity.componentName.className)

    override fun log(message: Any?, tag: Any?, priority: LogPriority) {
        log(message, tag, priority, Companion.isLoggingEnabled)
    }

    override fun log(message: Any?, priority: LogPriority) {
        log(message, tag, priority, Companion.isLoggingEnabled)
    }

    companion object : AbsLogger() {
        override var tag: Any? = null
        override var isLoggingEnabled: Boolean = true

        override fun log(message: Any?, tag: Any?, priority: LogPriority) {
            log(message, tag, priority, isLoggingEnabled)
        }

        override fun log(message: Any?, priority: LogPriority) {
            log(message, tag, priority, isLoggingEnabled)
        }

        private fun log(message: Any?, tag: Any?, priority: LogPriority, isEnabled: Boolean) {
            if (isEnabled) {
                AndroidLog.println(priority.value, tag.toString(), message.toString())
            }
        }
    }
}
