package com.dex.devlibrary.view

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.shapes.Shape
import android.view.View
import android.view.Window
import android.widget.LinearLayout
import androidx.core.view.marginEnd
import com.dex.devlibrary.R

class DialogUtils(context: Context?) : View(context) {
companion object{
    fun bound(v: View){
        v.background = v.context.getDrawable(R.drawable.shape_curved)
    }
}

}