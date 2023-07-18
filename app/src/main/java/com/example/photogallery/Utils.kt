package com.example.photogallery

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(strURL: String) {
    Glide.with(this.context)
        .load(strURL)
        .into(this)
}