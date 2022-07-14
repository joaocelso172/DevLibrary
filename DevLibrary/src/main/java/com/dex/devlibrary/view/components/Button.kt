package com.dex.devlibrary.view.components

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import com.dex.devlibrary.R

class Button : androidx.appcompat.widget.AppCompatButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs){
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs,
        defStyleAttr)
}
