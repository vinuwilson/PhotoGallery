package com.example.photogallery.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.photogallery.R

fun ImageView.loadImage(strURL: String) {
    Glide.with(this.context)
        .load(strURL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}