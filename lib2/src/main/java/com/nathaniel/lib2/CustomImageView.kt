package com.nathaniel.lib2

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import kotlin.random.Random
import android.graphics.drawable.GradientDrawable


open class CustomImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected val imageView: ImageView
    protected lateinit var palette: Palette

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
        val bitmap = AppCompatResources.getDrawable(context, resId)?.toBitmap()
        bitmap?.let {
            palette = Palette.from(bitmap).generate()
        }
        updateBackgroundColor()
    }

    open fun updateBackgroundColor() {
        imageView.background = getGradientDrawable(getRandomColor())
    }

    protected fun getRandomColor(): Int {
        val randomNumber = Random.nextInt(0, 6)
        return palette.swatches[randomNumber]?.rgb ?: ContextCompat.getColor(
            context,
            R.color.color_default_bg
        )
    }

    protected fun getGradientDrawable(color: Int): Drawable {
        val colors = IntArray(2)
        colors[0] = Color.WHITE
        colors[1] = color
        return GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, colors)
    }
}