package com.nathaniel.df

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.nathaniel.lib2.CustomImageView


class CustomImageViewWithLabel @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CustomImageView(context, attrs, defStyleAttr) {

    private val colorTextView: TextView = findViewById(R.id.text_view_color)

    override fun setImageRes(resId: Int) {
        val randomColor = getRandomColor()
        imageView.setImageResource(resId)
        imageView.setBackgroundColor(randomColor)
        colorTextView.text = String.format("#%06X", 0xFFFFFF and randomColor)
    }
}