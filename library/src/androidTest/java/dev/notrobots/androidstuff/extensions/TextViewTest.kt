package dev.notrobots.androidstuff.extensions

import android.content.Context
import android.widget.TextView
import androidx.test.platform.app.InstrumentationRegistry
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

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
    fun testSetErrorWhen_withString() {
        val textView = TextView(context)

        textView.setErrorWhen("Error") { s ->
            s.length < 14
        }

        textView.append("Test")
        assert(textView.hasErrors)

        textView.append("TestTestTest")
        assert(!textView.hasErrors)
    }

    @Test
    fun testSetError_withValidator() {
        val textView = TextView(context)

        textView.setError { s ->
            when {
                s.isEmpty() -> "String cannot be empty"
                s.length < 14 -> "Length must be at least 14"

                else -> null
            }
        }

        assert(textView.hasErrors)

        textView.append("hello")
        assert(textView.hasErrors)

        textView.append("hellohellohello")
        assert(!textView.hasErrors)
    }

    @Test
    fun testSetError_withValidators() {
        val textView = TextView(context)

        textView.setError(
            "String cannot be empty" to { it.isEmpty() },
            "Length must be at least 14" to { it.length < 14 }
        )

        assert(textView.hasErrors)

        textView.append("hello")
        assert(textView.hasErrors)

        textView.append("hellohellohello")
        assert(!textView.hasErrors)
    }
}