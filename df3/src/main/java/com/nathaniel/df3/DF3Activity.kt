package com.nathaniel.df3

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.AssetDataSource
import com.google.android.exoplayer2.upstream.DataSource
import com.nathaniel.lib2.CustomImageView


class DF3Activity : AppCompatActivity() {

    lateinit var player: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_df3_main)

        val customImageView = findViewById<CustomImageView>(R.id.custom_image_view)
        customImageView.setImageRes(R.drawable.ic_big_buck_bunny)

        playVideo()
    }

    /**
     * The sample video can be found at
     * https://peach.blender.org/download/
     * https://www.appsloveworld.com/download-sample-mp4-video-mp4-test-videos/
     */
    private fun playVideo() {
        val videoPlayer = findViewById<SimpleExoPlayerView>(R.id.video_player)
        val videoTrackSelectionFactory = AdaptiveTrackSelection.Factory(null)

        val trackSelector = DefaultTrackSelector(videoTrackSelectionFactory)

        player = ExoPlayerFactory.newSimpleInstance(this, trackSelector)
        videoPlayer.requestFocus()
        videoPlayer.useController = true
        videoPlayer.player = player

        val dataSourceFactory: DataSource.Factory = DataSource.Factory { AssetDataSource(this) }

        val videoSource = ExtractorMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse("assets:///sample_video.mp4"))
        player.prepare(videoSource)
        player.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}