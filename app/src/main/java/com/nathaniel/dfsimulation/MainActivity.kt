package com.nathaniel.dfsimulation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainActivity : AppCompatActivity() {

    private lateinit var splitInstallManager:SplitInstallManager
    private var sessionId = 0

    private val listener = SplitInstallStateUpdatedListener { state ->
        if (state.sessionId() == sessionId) {
            when (state.status()) {
                SplitInstallSessionStatus.FAILED -> {
                    Toast.makeText(
                        this,
                        "Install Module Error:${state.errorCode()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Toast.makeText(this, "Install Module Success", Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(
                    this,
                    "Install Module Error, Status: ${state.status()}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun attachBaseContext(context: Context) {
        super.attachBaseContext(context)
        SplitCompat.install(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splitInstallManager = SplitInstallManagerFactory.create(this)
        splitInstallManager.registerListener(listener)

        findViewById<Button>(R.id.button_go_to_df).setOnClickListener {
            installAndGoToDFPage()
        }
    }

    private fun installAndGoToDFPage() {
        if (!splitInstallManager.installedModules.contains(DF_MODULE_NAME)) {
            val request = SplitInstallRequest.newBuilder().addModule(DF_MODULE_NAME).build()
            splitInstallManager.startInstall(request).addOnSuccessListener { id -> sessionId = id }
                .addOnFailureListener {
                    Toast.makeText(this, "Install Module Error", Toast.LENGTH_SHORT).show()
                }
            return
        }
        try {
            val intent = Intent()
            intent.setClassName(packageName, DF_ACTIVITY_NAME)
            startActivity(intent)
        } catch (e:Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Could not open DF module, please try again", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        splitInstallManager.unregisterListener(listener)
    }

    companion object {
        const val DF_MODULE_NAME = "df"
        const val DF_ACTIVITY_NAME = "com.nathaniel.df.DFActivity"
    }
}