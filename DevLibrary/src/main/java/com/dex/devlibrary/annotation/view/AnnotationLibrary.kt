package com.dex.devlibrary.annotation.view

import android.view.View

class AnnotationLibrary {
    companion object {
        val DEFAULT = -1
        const val RESOURCE_ID = 1
        const val URL = 2
        const val DRAWABLE = 3
        @Target(
            AnnotationTarget.FUNCTION, AnnotationTarget.CLASS,
            AnnotationTarget.FIELD,
            AnnotationTarget.CONSTRUCTOR,
        )
        @Retention(AnnotationRetention.RUNTIME)
        annotation class Skip
        @Target(
            AnnotationTarget.FUNCTION, AnnotationTarget.CLASS,
            AnnotationTarget.FIELD,
            AnnotationTarget.CONSTRUCTOR,
        )
        @Retention(AnnotationRetention.RUNTIME)
        annotation class PrettyName(val n: String)
        @Target(
            AnnotationTarget.FIELD
        )
        @Retention(AnnotationRetention.RUNTIME)
        annotation class FieldType(val layoutType: Int)

    }
}