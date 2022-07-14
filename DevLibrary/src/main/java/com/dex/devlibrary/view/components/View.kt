package com.dex.devlibrary.view.components

import android.content.Context
import android.graphics.Rect
import android.view.View
import com.dex.devlibrary.R

class View(context: Context?) : View(context) {
    companion object{
        val DEFAULT_BORDER_WIDTH = 10
        val DEFAULT_BORDER_HEIGHT = 15
    }
    init {
        this.bound()
    }

    fun bound(){
        this.let {
            var backgroundShape = context.getDrawable(R.drawable.shape_curved).also {
                background = it
            }
        }
    }
}