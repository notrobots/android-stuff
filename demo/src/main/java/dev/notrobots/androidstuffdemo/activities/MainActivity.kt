package dev.notrobots.androidstuffdemo.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import dev.notrobots.androidstuff.extensions.makeToast
import dev.notrobots.androidstuff.extensions.startActivity
import dev.notrobots.androidstuff.util.AbsLogger
import dev.notrobots.androidstuff.util.Logger
import dev.notrobots.androidstuffdemo.R

class MainActivity : AppCompatActivity() {
    private val demos = listOf(
        ViewBindingsDemo::class
    )
    private val logger = Logger(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val names = demos.map {
            it.java.getField("title").get(null) ?: it.simpleName
        }
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
        val listView = findViewById<ListView>(R.id.list)

        listView.adapter = adapter
        listView.setOnItemClickListener { adapterView, view, i, l ->
            startActivity(demos[i])
        }

        try {
            val pInfo = packageManager.getPackageInfo(packageName, 0)
            val version = pInfo?.versionName

            makeToast(version)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        testLogger(logger)

        Logger.tag = "Static Logger"
        testLogger(Logger)
    }

    private fun testLogger(logger: AbsLogger) {
        logger.logw("This is a warning log", Exception("Blah blah blah"))
        logger.logi("This is an info log", Exception("Blah blah blah"))
        logger.logd("This is a debug log", Exception("Blah blah blah"))
        logger.loge("This is an error log", Exception("Blah blah blah"))
        logger.log("This is a verbose log", Exception("Blah blah blah"))
        logger.logwtf("This is a wtf log", Exception("Blah blah blah"))
    }
}