package com.dex.devlibrary.view.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.contains
import androidx.core.view.setPadding
import com.dex.devlibrary.R
import com.dex.devlibrary.annotation.view.AnnotationLibrary
import com.dex.devlibrary.image.utils.PicassoConfig
import com.dex.devlibrary.view.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.reflect.Field

class ObjectLayout(context: Context?) : ViewGroup(context!!) {
    constructor(context: Context, mObjectClass: Any) : this(context) {
        this.mObjectClass = mObjectClass
    }

    companion object {
        private val TAG = "ObjectLayout"
    }

    /**Object received from constructor, used to generate layout after attach to window*/
    private lateinit var mObjectClass: Any

    /**Layout attached to parent FrameLayout*/
    private lateinit var mBaseLinearLayout: LinearLayout

    override fun onAttachedToWindow() {
        Log.d(TAG, "onAttachedToWindow: ")
        setObjectLayout(mObjectClass)
        super.onAttachedToWindow()
    }

    /**Return if field content is drawable, image or resourceId*/
    private fun getViewType(field: Field): Int {
        if (field.isAnnotationPresent(AnnotationLibrary.Companion.FieldType::class.java)) {
            field.getAnnotation(AnnotationLibrary.Companion.FieldType::class.java).apply {
                return layoutType
            }
        }
        if (field.type == Drawable::class.java) {
            return AnnotationLibrary.DRAWABLE
        }
        return AnnotationLibrary.DEFAULT
    }

    /**Set ObjectLayout base layer*/
    private fun setBaseLayout() {
        this.rootView.background = context.getDrawable(R.drawable.shape_curved)
        this.layoutParams = FrameLayout.LayoutParams(0, 0)
        Utils.setAllMargins((this.parent as FrameLayout)!!, 35)
        (this.parent as FrameLayout)?.layoutParams =
            FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT)
        mBaseLinearLayout = LinearLayout(context).also {
            it.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
            it.orientation = LinearLayout.VERTICAL
            it.setPadding(20)
            (this.parent as FrameLayout)?.addView(it)
            TextView(context).let { title ->
                title.textSize
                title.textSize = 22f
                title.gravity = TextView.TEXT_ALIGNMENT_GRAVITY
                if (mObjectClass.javaClass.getAnnotation(AnnotationLibrary.Companion.PrettyName::class.java) != null) {
                    title.text =
                        mObjectClass.javaClass.getAnnotation(AnnotationLibrary.Companion.PrettyName::class.java).n
                }
                it.addView(title)
            }
        }
    }

    /**Set layout using object*/
    private fun setObjectLayout(objects: Any) {
        setBaseLayout()
        objects.javaClass.declaredFields.reversed().forEach {
            it.isAccessible = true
            setObjectField(it, objects)
            Log.d(TAG, "setObjectLayout: Field: ${it.name}, content: ${it.get(objects)}")
        }
    }

    /**Set field layout by content*/
    private fun setObjectField(field: Field, content: Any) {
        if ((field).isAnnotationPresent(AnnotationLibrary.Companion.Skip::class.java)) {
            return
        }
        val v: View
        when (getViewType(field)) {
            AnnotationLibrary.DRAWABLE, AnnotationLibrary.URL, AnnotationLibrary.RESOURCE_ID -> {
                ImageView(context).apply {
                    layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                    )
                    setImageForField(field, content, this)
                    setPadding(20)
                    v = this
                    Log.d(TAG, "setViewForField: View added as ImageView: ${getViewType(field)}")
                }
            }
            else -> {
                TextInputLayout(context).let { til ->
                    TextInputEditText(context).apply {
                        layoutParams = LinearLayout.LayoutParams(
                            LayoutParams.MATCH_PARENT,
                            LayoutParams.WRAP_CONTENT
                        )
                        setPadding(0, 50, 0, 0)
                        setBackgroundResource(android.R.color.transparent)
                        til.addView(this)
                    }
                    til.editText!!.apply {
                        setText(field.get(content).toString())
                        inputType = InputType.TYPE_NULL
                    }
                    til.hint = Utils.setFieldName(field)
                    til.layoutParams = LayoutParams(
                        LayoutParams.MATCH_PARENT,
                        LayoutParams.WRAP_CONTENT
                    )
                    Log.d(TAG, "setViewForField: View added as TIL: ${field.get(content)}")
                    v = til
                }
            }
        }
        if (!mBaseLinearLayout.contains(v)) mBaseLinearLayout.addView(v)
    }

    /**Set image for field*/
    private fun setImageForField(field: Field, content: Any, imageView: ImageView) {
        when (getViewType(field)) {
            AnnotationLibrary.DRAWABLE -> {
                imageView.setImageDrawable(field.get(content) as Drawable)
            }
            AnnotationLibrary.RESOURCE_ID -> {
                imageView.setImageResource(field.get(content) as Int)
            }
            AnnotationLibrary.URL -> {
                imageView.layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
                PicassoConfig.getPicasso(context).load(field.get(content).toString())
                    .placeholder(android.R.drawable.ic_menu_gallery).into(imageView)
            }
        }
    }

    override fun onLayout(p0: Boolean, p1: Int, p2: Int, p3: Int, p4: Int) {
    }
}