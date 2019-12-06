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

        val customImageViewLeft = findViewById<CustomImageView>(R.id.custom_image_view_left)
        val customImageViewRight = findViewById<CustomImageView>(R.id.custom_image_view_right)

        customImageViewLeft.setImageRes(getRandomImageRes())
        customImageViewRight.setImageRes(getRandomImageRes())

        findViewById<Button>(R.id.button_set_random_image).setOnClickListener {
            customImageViewLeft.setImageRes(getRandomImageRes())
            customImageViewRight.setImageRes(getRandomImageRes())
        }
        findViewById<Button>(R.id.button_set_icon_update_background).setOnClickListener {
            customImageViewLeft.updateBackground()
            customImageViewRight.updateBackground()
        }
    }

    private fun getRandomImageRes(): Int {
        val randomNumber = Random.nextInt(0, IMAGE_LIST.size)
        return IMAGE_LIST[randomNumber]
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    /**
     * https://freebiesupply.com/blog/popular-red-logos/
     */
    companion object {
        val IMAGE_LIST = arrayOf(
            com.nathaniel.lib1.R.drawable.ic_android,
            com.nathaniel.lib1.R.drawable.ic_android_studio,
            R.drawable.ic_airbnb,
            R.drawable.ic_android_new_logo,
            R.drawable.ic_android_oreo,
            R.drawable.ic_apple,
            R.drawable.ic_batman,
            R.drawable.ic_beats_electronics,
            R.drawable.ic_bmw,
            R.drawable.ic_burger_king,
            R.drawable.ic_canon_inc,
            R.drawable.ic_coca_cola,
            R.drawable.ic_exxon,
            R.drawable.ic_facebook,
            R.drawable.ic_firebase,
            R.drawable.ic_gojek,
            R.drawable.ic_google,
            R.drawable.ic_google_chrome,
            R.drawable.ic_harley_davidson,
            R.drawable.ic_heinz,
            R.drawable.ic_html_5,
            R.drawable.ic_kmart,
            R.drawable.ic_levis,
            R.drawable.ic_marvel,
            R.drawable.ic_microsoft,
            R.drawable.ic_netflix,
            R.drawable.ic_opel,
            R.drawable.ic_opensuse,
            R.drawable.ic_oracle,
            R.drawable.ic_oreo,
            R.drawable.ic_philips,
            R.drawable.ic_photoshop,
            R.drawable.ic_php,
            R.drawable.ic_tokopedia,
            R.drawable.ic_virgin
        )
    }
}
