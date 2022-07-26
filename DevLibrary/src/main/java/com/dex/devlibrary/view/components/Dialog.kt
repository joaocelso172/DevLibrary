package com.dex.devlibrary.view.components

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.View
import android.view.Window


open class Dialog(context: Context) : Dialog(context) {
    companion object {
        private val DEFAULT_MARGIN_SIZE = 15
        private val AUTO_GENERATE_VIEW = -1
        private val TAG = "DevLibrary.Dialog"
    }

    constructor(context: Context, mObjectClass: Any) : this(context) {
        setContentView(AUTO_GENERATE_VIEW, mObjectClass)
    }

    constructor(context: Context, mObjectLayout: ObjectLayout) : this(context) {
        setContentView(mObjectLayout)
    }

    private fun setContentView(layoutResID: Int, mObjectClass: Any) {
        if (layoutResID == AUTO_GENERATE_VIEW) {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            val mObjectLayout = ObjectLayout(context, mObjectClass)
            Log.d(TAG, "setContentView: $mObjectLayout")
            super.setContentView(mObjectLayout)
        }
    }

    final override fun setContentView(view: View) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.setContentView(view)
    }
}