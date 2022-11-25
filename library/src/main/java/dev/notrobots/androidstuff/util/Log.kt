package dev.notrobots.androidstuff.util

import android.app.Activity
import android.util.Log as AndroidLog

interface ILog {
    /**
     * Whether or not the logging is enabled for this logger.
     */
    var isLoggingEnabled: Boolean

    /**
     * Tag used for the logs.
     */
    var tag: Any?

    fun log(message: Any?, tag: Any?, priority: LogPriority)

    fun log(message: Any?, priority: LogPriority)

    fun loge(message: Any?) = log(message, LogPriority.Error)

    fun logd(message: Any?) = log(message, LogPriority.Debug)

    fun logi(message: Any?) = log(message, LogPriority.Info)

    fun log(message: Any?) = log(message, LogPriority.Verbose)

    fun logw(message: Any?) = log(message, LogPriority.Warn)

    fun loga(message: Any?) = log(message, LogPriority.Assert)

    fun loge(exception: Exception, full: Boolean = true) {
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

class Log(tag: Any?) : ILog {
    override var tag: Any? = tag
    override var isLoggingEnabled: Boolean = true

    constructor(activity: Activity) : this(activity.componentName.className)

    override fun log(message: Any?, tag: Any?, priority: LogPriority) {
        if (isLoggingEnabled) {
            AndroidLog.println(priority.value, tag.toString(), message.toString())
        }
    }

    override fun log(message: Any?, priority: LogPriority) {
        if (isLoggingEnabled) {
            AndroidLog.println(priority.value, tag.toString(), message.toString())
        }
    }

    companion object : ILog {
        override var tag: Any? = null
        override var isLoggingEnabled: Boolean = true

        override fun log(message: Any?, tag: Any?, priority: LogPriority) {
            if (isLoggingEnabled) {
                AndroidLog.println(priority.value, tag.toString(), message.toString())
            }
        }

        override fun log(message: Any?, priority: LogPriority) {
            if (isLoggingEnabled) {
                AndroidLog.println(priority.value, tag.toString(), message.toString())
            }
        }
    }
}
