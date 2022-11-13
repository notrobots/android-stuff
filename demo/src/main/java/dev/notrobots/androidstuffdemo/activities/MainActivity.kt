package dev.notrobots.androidstuffdemo.activities

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import dev.notrobots.androidstuff.extensions.makeToast
import dev.notrobots.androidstuff.extensions.startActivity
import dev.notrobots.androidstuffdemo.R

class MainActivity : AppCompatActivity() {
    private val demos = listOf(
        ViewBindingsDemo::class
    )

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
    }
}