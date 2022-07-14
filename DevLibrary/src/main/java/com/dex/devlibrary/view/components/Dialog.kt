package com.dex.devlibrary.view.components

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.size
import com.dex.devlibrary.R
import com.dex.devlibrary.view.Utils
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.reflect.Field
import kotlin.reflect.KClass

open class Dialog(context: Context) : Dialog(context) {

    @Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS,
        AnnotationTarget.PROPERTY,
        AnnotationTarget.VALUE_PARAMETER,
        AnnotationTarget.LOCAL_VARIABLE,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.FIELD,
        AnnotationTarget.TYPE_PARAMETER,
        AnnotationTarget.CONSTRUCTOR,
    )
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Skip()
    annotation class PrettyName(val n: String)
    companion object{
        private val ROOT_LINEAR_LAYOUT = "l1"
        private val SURFACE_LINEAR_LAYOUT = "l2"
        private val DEFAULT_MARGIN_SIZE = 15
        val AUTO_GENERATE_VIEW = -1
        private val TAG = "DevLibrary.Dialog"
    }

    var mapFields = HashMap<Field, View>()
    private lateinit var view: View
    private lateinit var frameLayout: FrameLayout
    private lateinit var linearLayout: LinearLayout
    private lateinit var genericClass: Class<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(AUTO_GENERATE_VIEW)
    }

    override fun setContentView(layoutResID: Int) {
        window!!.let { w ->
            w.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            w.decorView.findViewById<View>(android.R.id.content).let { v ->
                view = v
                frameLayout = view as FrameLayout
                v.background = context.getDrawable(R.drawable.shape_curved)
                Utils.setAllMargins(v, 35)
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                if (layoutResID != AUTO_GENERATE_VIEW){
                    super.setContentView(layoutResID)
                }
            }
        }

    }

    fun <T: Any> createViewModel(t: Class<T>) {
        genericClass = t
        frameLayout.layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        linearLayout = LinearLayout(context).also {
            it.layoutParams = LinearLayout.LayoutParams(650, 650)
            it.orientation = LinearLayout.VERTICAL
            Utils.setAllMargins(it, DEFAULT_MARGIN_SIZE)
            frameLayout.addView(it)
            TextView(context).let { title ->
                title.textSize
                title.textSize = 22f
                title.gravity = TextView.TEXT_ALIGNMENT_GRAVITY
                if (t.getAnnotation(PrettyName::class.java) != null){
                    title.text = t.getAnnotation(PrettyName::class.java).n
                }
                it.addView(title)
            }
        }

        for (field in  t.declaredFields){
            val fin = field.isAnnotationPresent(Skip::class.java)

            Log.d(TAG, "createViewModel: ${field.toGenericString()} ${field.type} $fin")
            TextInputLayout(context).let {
                var editText = TextInputEditText(context)
                it.addView(editText)
                mapFields[field] = it
            }
        }
    }

    fun setObject(objects: Any) {
        Log.d(TAG, "genericFunc: ${genericClass.cast(objects)}")
        genericClass.declaredFields.forEach {
            it.isAccessible = true
            Log.d(TAG, "genericFunc: ${it.get(objects)} ${it.type}")
            var element = mapFields[it] as TextInputLayout
            element.helperText = it.name
            element.editText!!.inputType = InputType.TYPE_NULL
            element.editText!!.setText(it.get(objects).toString())
            element.editText!!.isClickable = false
            linearLayout.addView(element)
        }
    }

    fun getView(){
        var mapView = HashMap<String, View>()
    }


    fun bound(mapDialogLinearLayout: HashMap<String, LinearLayout>){
        var l1: LinearLayout
        var l2: LinearLayout

        mapDialogLinearLayout.let { mapLinear ->
            mapLinear[ROOT_LINEAR_LAYOUT]!!.also { l1 = it}
            mapLinear[SURFACE_LINEAR_LAYOUT]!!.also { l2 = it }
        }

        l1?.let {
            it.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            it.background = context.getDrawable(R.drawable.shape_curved)
            it.orientation = LinearLayout.HORIZONTAL
        }

        l2?.let {
            it.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
            Utils.setAllMargins(it, DEFAULT_MARGIN_SIZE)
            it.orientation = LinearLayout.VERTICAL
        }

    }
}