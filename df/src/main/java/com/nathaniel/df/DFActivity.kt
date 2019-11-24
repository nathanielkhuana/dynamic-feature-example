package com.nathaniel.df

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nathaniel.lib2.CustomImageView

class DFActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_df_main)

        val customImageView = findViewById<CustomImageViewWithLabel>(R.id.custom_image_view)
        findViewById<Button>(R.id.button_set_icon_android).setOnClickListener {
            customImageView.setImageRes(com.nathaniel.lib1.R.drawable.ic_android_symbol)
        }
        findViewById<Button>(R.id.button_set_icon_android_studio).setOnClickListener {
            customImageView.setImageRes(com.nathaniel.lib1.R.drawable.ic_android_studio)
        }
    }
}
