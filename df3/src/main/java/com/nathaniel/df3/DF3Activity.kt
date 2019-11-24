package com.nathaniel.df3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nathaniel.lib2.CustomImageView

class DF3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_df3_main)

        val customImageView = findViewById<CustomImageView>(R.id.custom_image_view)
    }
}