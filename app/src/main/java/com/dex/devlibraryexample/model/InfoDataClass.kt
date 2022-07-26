package com.dex.devlibraryexample.model

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import com.dex.devlibrary.annotation.view.AnnotationLibrary
import com.dex.devlibrary.annotation.view.AnnotationLibrary.Companion.RESOURCE_ID
import com.dex.devlibrary.annotation.view.AnnotationLibrary.Companion.URL
import com.dex.devlibrary.view.components.Dialog
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@AnnotationLibrary.Companion.PrettyName("Info Data Class")
data class InfoDataClass(
    @AnnotationLibrary.Companion.Skip
    var id: Int,
    @AnnotationLibrary.Companion.PrettyName("Title")
    var name: String,
    @AnnotationLibrary.Companion.FieldType(RESOURCE_ID)
    @DrawableRes
    var image: Int,
    @AnnotationLibrary.Companion.Skip
    var imageDrawable: Drawable,
    @AnnotationLibrary.Companion.FieldType(URL)
    var imageUrl: String
    )