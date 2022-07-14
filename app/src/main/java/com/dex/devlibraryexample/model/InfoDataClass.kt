package com.dex.devlibraryexample.model

import com.dex.devlibrary.view.components.Dialog
import java.lang.annotation.ElementType
import java.lang.annotation.RetentionPolicy

@Dialog.PrettyName("Info Data Class")
data class InfoDataClass(
    @get:Dialog.Skip var id: Int,
    @Dialog.Skip var name: String
    )