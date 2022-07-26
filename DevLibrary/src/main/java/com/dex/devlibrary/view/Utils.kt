package com.dex.devlibrary.view

import android.view.View
import android.view.ViewGroup
import androidx.core.view.setMargins
import com.dex.devlibrary.annotation.view.AnnotationLibrary
import com.dex.devlibrary.view.components.Dialog
import java.lang.reflect.Field

class Utils {
    companion object {
        fun setAllMargins(v: View, margin: Int) {
            val param = v.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(margin)
            v.layoutParams = param
        }

        fun setFieldName(field: Field) : String{
            if (field.isAnnotationPresent(AnnotationLibrary.Companion.PrettyName::class.java)){
                return field.getAnnotation(AnnotationLibrary.Companion.PrettyName::class.java).n
            }
            return field.name
        }

    }
}