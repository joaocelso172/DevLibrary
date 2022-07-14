package com.dex.devlibrary.view

import android.view.View
import android.view.ViewGroup
import androidx.core.view.setMargins

class Utils {
    companion object {
        fun setAllMargins(v: View, margin: Int) {
            val param = v.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(margin)
            v.layoutParams = param
        }
    }
}