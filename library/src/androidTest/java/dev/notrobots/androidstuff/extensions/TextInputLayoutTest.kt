package dev.notrobots.androidstuff.extensions

import android.content.Context
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.UiThread
import androidx.test.platform.app.InstrumentationRegistry
import com.google.android.material.textfield.TextInputLayout
import dev.notrobots.androidstuff.R
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class TextInputLayoutTest : TestCase() {
    private lateinit var context: Context

    @Before
    override fun setUp() {
        super.setUp()

        context = InstrumentationRegistry.getInstrumentation().targetContext
        context.setTheme(R.style.Theme_AppCompat_Light)
    }

    @Test
    @UiThread
    fun testGetHasErrors() {
        val textView = createTextInputLayout()

        textView.post {
            textView.error = "Error"
            assert(textView.hasErrors)

            textView.error = null
            assert(!textView.hasErrors)

            textView.error = ""
            assert(!textView.hasErrors)
        }
    }

    @Test
    @UiThread
    fun testSetClearErrorOnType() {
        val textView = createTextInputLayout()

        textView.post {
            textView.error = "Error"
            textView.setClearErrorOnType()
            textView.editText?.append("Test")

            assert(!textView.hasErrors)
        }
    }

    @Test
    @UiThread
    fun testSetErrorWhen_withString() {
        val textView = createTextInputLayout()

        textView.post {
            textView.setErrorWhen("Error") { s ->
                s.length < 14
            }

            textView.editText?.append("Test")
            assert(textView.hasErrors)

            textView.editText?.append("TestTestTest")
            assert(!textView.hasErrors)
        }
    }

    @Test
    @UiThread
    fun testSetError_withValidator() {
        val textView = createTextInputLayout()

        textView.post {
            textView.setError { s ->
                when {
                    s.isEmpty() -> "String cannot be empty"
                    s.length < 14 -> "Length must be greater than 14"

                    else -> null
                }
            }

            assert(textView.hasErrors)

            textView.editText?.append("hello")
            assert(textView.hasErrors)

            textView.editText?.append("hellohellohello")
            assert(!textView.hasErrors)
        }
    }

    @Test
    @UiThread
    fun testSetError_withValidators() {
        val textView = createTextInputLayout()

        textView.post {
            textView.setError(
                "String cannot be empty" to { it.isEmpty() },
                "Length must be at least 14" to { it.length < 14 }
            )

            assert(textView.hasErrors)

            textView.editText?.append("hello")
            assert(textView.hasErrors)

            textView.editText?.append("hellohellohello")
            assert(!textView.hasErrors)
        }
    }

    private fun createTextInputLayout(): TextInputLayout {
        val editText = EditText(context)
        val editTextParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
        val textInputLayout = TextInputLayout(context)
        val textInputLayoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        textInputLayout.layoutParams = textInputLayoutParams
        textInputLayout.addView(editText, editTextParams)

        return textInputLayout
    }
}