package com.example.userprofile

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(strURL: String) {
    Glide.with(this.context)
        .load(strURL)
        .placeholder(R.drawable.placeholder)
        .into(this)
}