package com.nathaniel.lib2

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import java.util.*


open class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected var imageView: ImageView

    init {
        View.inflate(context, getLayoutId(), this)
        imageView = findViewById(getImageViewId())
    }

    open fun getLayoutId(): Int {
        return R.layout.custom_image_view
    }

    open fun getImageViewId(): Int {
        return R.id.image_view
    }

    open fun setImageRes(resId: Int) {
        imageView.setImageResource(resId)
        updateBackgroundColor()
    }

    open fun updateBackgroundColor(){
        imageView.setBackgroundColor(getRandomColor())
    }

    protected fun getRandomColor(): Int {
        val random = Random()
        return Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
    }
}