package com.nathaniel.dfsimulation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.button_go_to_df).setOnClickListener {
            val intent = Intent()
            intent.setClassName(this, "com.nathaniel.df.DFActivity")
            startActivity(intent)
        }
    }
}