package com.dex.devlibraryexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dex.devlibrary.view.components.Dialog
import com.dex.devlibrary.view.components.View
import com.dex.devlibraryexample.dialog.DialogExample

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnShowDialog = findViewById<Button>(R.id.btn_show_dialog).also {
            it.setOnClickListener {
                showCustomDialog()
            }
        }
    }

    fun showCustomDialog(){
         var dialog = DialogExample(this)
         dialog.show()
    }
}