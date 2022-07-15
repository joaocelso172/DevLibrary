package com.dex.devlibraryexample.dialog

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.dex.devlibrary.view.DialogUtils
import com.dex.devlibrary.view.components.Dialog
import com.dex.devlibraryexample.R
import com.dex.devlibraryexample.model.InfoDataClass

class DialogExample(context: Context) : Dialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tv_dynamic = TextView(context)
        tv_dynamic.textSize = 38f
        tv_dynamic.text = "This is a dynamic TextView generated programmatically"
        /**TODO Create elements using Kotlin + create template package to reuse in diverses views*/
        val infoDataClass = InfoDataClass(1, "teste")
        this.createViewModel(InfoDataClass::class.java)

        Log.d("DialogExample", "onCreate: ${this.setObject(infoDataClass)}")
    }

}