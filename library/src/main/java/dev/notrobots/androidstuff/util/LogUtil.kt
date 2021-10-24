package dev.notrobots.androidstuff.util

import android.util.Log
import dev.notrobots.androidstuff.util.LogUtil.logsEnabled

/**
 * Logging utility
 */
object LogUtil {
    internal var tag = ""
    internal var logsEnabled = true

    fun setTag(tag: String) {
        this.tag = tag
    }

    fun setLogsEnabled(logsEnabled: Boolean) {
        this.logsEnabled = logsEnabled
    }
}

//FIXME: Move the methods inside the class?

fun logd(message: Any? = "") {
    if (logsEnabled) {
        Log.d(LogUtil.tag, message.toString())
    }
}

fun loge(error: Any? = "") {
    if (logsEnabled) {
        Log.e(LogUtil.tag, error.toString())
    }
}

fun loge(error: Exception, full: Boolean = true) {
    if (logsEnabled) {
        Log.e(LogUtil.tag, (if (full) error else error.message).toString())
    }
}

fun logw(message: Any? = "") {
    if (logsEnabled) {
        Log.w(LogUtil.tag, message.toString())
    }
}

fun logi(message: Any? = "") {
    if (logsEnabled) {
        Log.i(LogUtil.tag, message.toString())
    }
}

fun logwtf(message: Any? = "") {
    if (logsEnabled) {
        Log.wtf(LogUtil.tag, message.toString())
    }
}