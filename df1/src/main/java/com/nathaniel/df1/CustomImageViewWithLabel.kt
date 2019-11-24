package com.nathaniel.df1

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.nathaniel.lib2.CustomImageView


class CustomImageViewWithLabel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CustomImageView(context, attrs, defStyleAttr) {

    private var colorTextView: TextView = findViewById(R.id.text_view_color)

    override fun getLayoutId(): Int {
        return R.layout.custom_image_view_with_label
    }

    override fun getImageViewId(): Int {
        return R.id.image_view
    }

    override fun updateBackgroundColor() {
        val randomColor = getRandomColor()
        imageView.setBackgroundColor(randomColor)
        colorTextView.text = String.format("#%06X", 0xFFFFFF and randomColor)
    }
}