package com.nathaniel.df1

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nathaniel.lib2.CustomImageView
import kotlin.random.Random

class DF1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_df1_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val customImageView = findViewById<CustomImageView>(R.id.custom_image_view)

        customImageView.setImageRes(getRandomImageRes())

        findViewById<Button>(R.id.button_set_random_image).setOnClickListener {
            customImageView.setImageRes(getRandomImageRes())
        }
        findViewById<Button>(R.id.button_set_icon_update_background).setOnClickListener {
            customImageView.updateBackground()
        }
    }

    private fun getRandomImageRes() :Int {
        val randomNumber = Random.nextInt(0, IMAGE_LIST.size)
        return IMAGE_LIST[randomNumber]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    companion object {
        val IMAGE_LIST = arrayOf(
            com.nathaniel.lib1.R.drawable.ic_android,
            com.nathaniel.lib1.R.drawable.ic_android_studio,
            R.drawable.ic_google,
            R.drawable.ic_google_chrome,
            R.drawable.ic_android_new_logo,
            R.drawable.ic_android_oreo,
            R.drawable.ic_firebase)
    }
}
