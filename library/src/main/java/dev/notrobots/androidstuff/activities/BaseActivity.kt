package dev.notrobots.androidstuff.activities

import dev.notrobots.androidstuff.extensions.makeToast

abstract class BaseActivity : ThemedActivity() {
    private var backPressedTime = 0L
    protected var doubleBackPressToExitEnabled = false
    protected var doubleBackPressDelay = DEFAULT_BACK_PRESSED_DELAY

    /**
     * Invoked when the user presses the back button but the time since
     * the last back press is higher than [doubleBackPressDelay]
     */
    open fun onBackPressedMessage() {
        makeToast("Press back again to exit")
    }

    /**
     * Invoked when the user double presses the back button
     *
     * This is only invoked if [doubleBackPressToExitEnabled] is true
     */
    open fun onDoubleBackPressed() {}

    override fun onBackPressed() {
        if (!doubleBackPressToExitEnabled) {
            super.onBackPressed()
            return
        }

        if (backPressedTime + doubleBackPressDelay > System.currentTimeMillis()) {
            super.onBackPressed()
            onDoubleBackPressed()
            return
        } else {
            onBackPressedMessage()
        }

        backPressedTime = System.currentTimeMillis()
    }

    companion object {
        const val DEFAULT_BACK_PRESSED_DELAY = 1500
    }
}