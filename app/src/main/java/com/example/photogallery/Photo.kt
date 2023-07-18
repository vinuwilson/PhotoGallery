package com.example.photogallery

data class Photo(
    val datetaken: String,
    val datetakengranularity: Int,
    val datetakenunknown: String,
    val description: Description,
    val farm: Int,
    val height_m: Int,
    val height_s: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val ownername: String,
    val secret: String,
    val server: String,
    val tags: String,
    val title: String,
    val url_m: String,
    val url_s: String,
    val width_m: Int,
    val width_s: Int
)