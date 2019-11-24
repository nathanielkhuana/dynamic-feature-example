package com.nathaniel.df2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.barteksc.pdfviewer.PDFView
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle

class DF2Activity : AppCompatActivity(), OnPageChangeListener, OnLoadCompleteListener,
    OnPageErrorListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_df2_main)
        findViewById<View>(R.id.view_page).setBackgroundResource(com.nathaniel.lib1.R.color.color_black)

        openPDF()
    }

    /**
     * The PDF file can be found at
     * https://file-examples.com/index.php/sample-documents-download/sample-pdf-download/
     */
    fun openPDF(){
        val pdfView = findViewById<PDFView>(R.id.pdf_view)
        pdfView.fromAsset("sample_pdf.pdf")
            .defaultPage(1)
            .onPageChange(this)
            .enableAnnotationRendering(true)
            .onLoad(this)
            .scrollHandle( DefaultScrollHandle (this))
            .spacing(10) // in dp
            .onPageError(this)
            .load()
    }

    override fun onPageChanged(page: Int, pageCount: Int) {

    }

    override fun loadComplete(nbPages: Int) {

    }

    override fun onPageError(page: Int, t: Throwable?) {

    }
}
