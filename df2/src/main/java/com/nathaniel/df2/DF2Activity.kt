package com.nathaniel.df2

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class DF2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_df2_main)

        findViewById<Button>(R.id.view_page).setBackgroundResource(com.nathaniel.lib1.R.color.color_red)
    }
}
