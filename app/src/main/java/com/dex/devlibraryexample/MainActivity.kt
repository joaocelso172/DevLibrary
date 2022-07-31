package com.dex.devlibraryexample

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.dex.devlibrary.view.components.Dialog
import com.dex.devlibrary.view.components.ObjectLayout
import com.dex.devlibraryexample.model.InfoDataClass
import java.lang.reflect.Field

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_show_dialog).apply {
            setOnClickListener {
                val myImage: Drawable? = ResourcesCompat.getDrawable(this.resources, R.drawable.ic_launcher_foreground, null)
                val infoDataClass = InfoDataClass(1, "jão", R.drawable.ic_launcher_foreground, myImage!!, "http://i.imgur.com/DvpvklR.png")
                buildCustomLayoutDialog(infoDataClass)
            }
        }
    }

    private fun showCustomDialog(info: InfoDataClass){
        /**Dialog customized instance*/
        Dialog(this, info).show()
        /**Another Dialog customized instance*/
        val objectLayout = ObjectLayout(this, info)
        Dialog(this, objectLayout)
        /**Default Dialog instance*/
        Dialog(this).apply {
            setContentView(objectLayout)
            show()
        }
    }

    private fun buildCustomLayoutDialog(info: InfoDataClass){
        val objectLayout = ObjectLayout(this, info)
        info.javaClass.declaredFields.forEach {
            Log.d(TAG, "buildCustomLayoutDialog: ${objectLayout.getLayoutField(info.name.hashCode())}  ${info.javaClass.getDeclaredField(it.name)}")

        }
        //Log.d(TAG, "buildCustomLayoutDialog: ${info.javaClass.getField(info.name.javaClass.name)}")
        Dialog(this).apply {
            setContentView(objectLayout)
            show()
        }

    }
}