package com.nathaniel.lib2

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout


class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val icon: ImageView

    init {
        View.inflate(context, R.layout.custom_view, this)
        icon = findViewById(R.id.image_view)
    }
}