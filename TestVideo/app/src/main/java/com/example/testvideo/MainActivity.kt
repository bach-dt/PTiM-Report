package com.example.testvideo

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.number.NumberFormatter.with
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import com.squareup.picasso.Picasso
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoUrl = "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4"
        val videoView = findViewById<VideoView>(R.id.videoView)
        val uri = Uri.parse(videoUrl);

        videoView.setVideoURI(uri)
        videoView.start()

        val imageView = findViewById<ImageView>(R.id.imageView)
        val imageURL = "http://172.20.10.5/cam-lo.jpg"


        Log.e("", "Bach")
        Picasso.with (this)
            .load(Uri.parse(imageURL))
            .into(imageView)

    }

}