package com.nathaniel.dfexample

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.*
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus

class MainActivity : AppCompatActivity() {

    private lateinit var splitInstallManager: SplitInstallManager
    private lateinit var progressTextView: TextView
    private lateinit var downloadedModuleName: String

    private var sessionId = 0

    private val listener = SplitInstallStateUpdatedListener { state ->
        if (state.sessionId() == sessionId) {
            when (state.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    updateDownloadProgress(state)
                }
                SplitInstallSessionStatus.REQUIRES_USER_CONFIRMATION -> {
                    splitInstallManager.startConfirmationDialogForResult(
                        state, this,
                        DOWNLOAD_CONFIRMATION_REQUEST_CODE
                    )
                }
                SplitInstallSessionStatus.FAILED -> {
                    Toast.makeText(
                        this, "Install Module Error:${state.errorCode()}", Toast.LENGTH_SHORT
                    ).show()
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Toast.makeText(this, "Install Module Success", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    // Do nothing
                }
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

        progressTextView = findViewById(R.id.text_view_download_progress)
        findViewById<Button>(R.id.button_go_to_df1).setOnClickListener {
            installAndGoToDFPage(MODULE_NAME_DF1, ACTIVITY_NAME_DF1)
        }
        findViewById<Button>(R.id.button_go_to_df2).setOnClickListener {
            installAndGoToDFPage(MODULE_NAME_DF2, ACTIVITY_NAME_DF2)
        }
        findViewById<Button>(R.id.button_go_to_df3).setOnClickListener {
            installAndGoToDFPage(MODULE_NAME_DF3, ACTIVITY_NAME_DF3)
        }
    }

    private fun installAndGoToDFPage(moduleName: String, activityName: String) {
        if (!splitInstallManager.installedModules.contains(moduleName)) {
            downloadedModuleName = moduleName
            val request = SplitInstallRequest.newBuilder().addModule(moduleName).build()
            splitInstallManager.startInstall(request).addOnSuccessListener { id -> sessionId = id }
                .addOnFailureListener {
                    Toast.makeText(this, "Install Module Error", Toast.LENGTH_SHORT).show()
                }
            return
        }
        try {
            val intent = Intent()
            intent.setClassName(packageName, activityName)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Could not open DF module, please try again", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun updateDownloadProgress(sessionState: SplitInstallSessionState) {
        val totalBytesToDownload = sessionState.totalBytesToDownload().toInt()
        val bytesDownloaded = sessionState.bytesDownloaded().toInt()
        val byteDownloadText = String.format("%.2f MB", totalBytesToDownload.toFloat() / ONE_MB)
        val percentageText =
            String.format("%.0f%%", bytesDownloaded.toFloat() * 100 / totalBytesToDownload)
        progressTextView.text =
            getString(
                R.string.downloading_status, downloadedModuleName, byteDownloadText, percentageText
            )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == DOWNLOAD_CONFIRMATION_REQUEST_CODE) {
            progressTextView.text = ""
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        splitInstallManager.unregisterListener(listener)
    }

    companion object {
        const val MODULE_NAME_DF1 = "df1"
        const val MODULE_NAME_DF2 = "df2"
        const val MODULE_NAME_DF3 = "df3"
        const val ACTIVITY_NAME_DF1 = "com.nathaniel.df1.DF1Activity"
        const val ACTIVITY_NAME_DF2 = "com.nathaniel.df2.DF2Activity"
        const val ACTIVITY_NAME_DF3 = "com.nathaniel.df3.DF3Activity"
        private const val DOWNLOAD_CONFIRMATION_REQUEST_CODE = 1
        private const val ONE_MB = 1024 * 1024
    }
}