package com.nathaniel.df

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.nathaniel.lib2.CustomView

class DFActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_df_main)

        val customView = findViewById<CustomView>(R.id.custom_view)
        findViewById<Button>(R.id.button_set_icon_android).setOnClickListener {
            customView.icon.setImageResource(com.nathaniel.lib1.R.drawable.ic_android_symbol)
            customView.setBackgroundResource(com.nathaniel.lib1.R.color.color_grey)
        }
        findViewById<Button>(R.id.button_set_icon_android_studio).setOnClickListener {
            customView.icon.setImageResource(com.nathaniel.lib1.R.drawable.ic_android_studio)
            customView.setBackgroundResource(com.nathaniel.lib1.R.color.color_red)
        }
    }
}
