package dev.notrobots.androidstuff.extensions

import android.content.Context
import android.widget.TextView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class TextViewTest : TestCase() {
    private lateinit var context: Context

    @Before
    override fun setUp() {
        super.setUp()

        context = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun testGetHasErrors() {
        val textView = TextView(context)

        textView.error = "Error"
        assert(textView.hasErrors)

        textView.error = null
        assert(!textView.hasErrors)

        textView.error = ""
        assert(!textView.hasErrors)
    }

    @Test
    fun testSetClearErrorOnType() {
        val textView = TextView(context)

        textView.error = "Error"
        textView.setClearErrorOnType()
        textView.append("Test")

        assert(!textView.hasErrors)
    }

    @Test
    fun testSetErrorWhen() {
        val textView = TextView(context)

        textView.setErrorWhen("Error") { s ->
            (s?.length ?: 0) < 14
        }

        textView.append("Test")
        assert(textView.hasErrors)

        textView.append("TestTestTest")
        assert(!textView.hasErrors)
    }
}