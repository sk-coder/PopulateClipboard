package com.karneth.test.populateclipboard

import android.content.ClipData
import android.content.ClipData.*
import android.content.ClipboardManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    val HTML_VALID_PHONE_NUMBER = "<html><body>954-555-1212</body></html>"
    val HTML_INVALID_PHONE_NUMBER = "<html><body>011954-555-1212</body></html>"
    val HTML_START = "<html><body>"
    val HTML_END   = "</body></html>"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_good_plain_text -> {
                Log.i("Clip", "Good Plain Text Copy Selected")
                // Get a handle to the Clipboard Service
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // build the string to be put into the clipboard
                val clipText = validNumber.text.toString()

                // Create the ClipData object with the new text
                val clip :ClipData = newPlainText("good simple text", clipText)

                // Set the clip as the primary clip item
                clipboard.setPrimaryClip(clip)

                return true    // Needed to operate with Option Item Selected
            }
            R.id.action_bad_plain_text -> {
                Log.i("Clip", "Bad Plain Text Copy Selected")
                // Get a handle to the Clipboard Service
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // build the string to be put into the clipboard
                val clipText = invalidNumber.text.toString()

                // Create the ClipData object with the new text
                val clip :ClipData = newPlainText("BAD simple text", clipText)

                // Set the clip as the primary clip item
                clipboard.setPrimaryClip(clip)

                return true    // Needed to operate with Option Item Selected
            }
            R.id.action_good_html_text -> {
                Log.i("Clip", "HTML Good Number Copy Selected")
                // Get a handle to the Clipboard Service
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // Create the HTML String
                val clipText = HTML_START + validNumber.text.toString() + HTML_END

                // Create the ClipData object with the new text
                val clip :ClipData = newHtmlText("HTML good text", validNumber.text.toString(), clipText)

                // Set the clip as the primary clip item
                clipboard.setPrimaryClip(clip)

                return true    // Needed to operate with Option Item Selected
            }
            R.id.action_bad_html_text -> {
                Log.i("Clip", "HTML BAD Number Copy Selected")
                // Get a handle to the Clipboard Service
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // Create the HTML String
                val clipText = HTML_START + invalidNumber.text.toString() + HTML_END

                // Create the ClipData object with the new text
                val clip :ClipData = newHtmlText("HTML BAD text", validNumber.text.toString(), clipText)

                // Set the clip as the primary clip item
                clipboard.setPrimaryClip(clip)

                return true    // Needed to operate with Option Item Selected
            }
            R.id.action_text_raw_uri -> {
                Log.i("Clip", "Raw URI Copy Selected")
                // Get a handle to the Clipboard Service
                val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // Create the ClipData object with the new text
                val clip :ClipData = newRawUri("Raw URI", Uri.parse("android.resource://com.karneth.test.populateclipboard/raw/valid_number.txt"))

                // Set the clip as the primary clip item
                clipboard.setPrimaryClip(clip)

                return true    // Needed to operate with Option Item Selected
            }
            else -> super.onOptionsItemSelected(item)

        }
    }
}
