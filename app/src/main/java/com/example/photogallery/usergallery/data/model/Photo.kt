package com.example.photogallery.usergallery.data.model

data class Photo(
    val datetaken: String,
    val description: Description,
    val farm: Int,
    val id: String,
    val owner: String,
    val ownername: String,
    val secret: String,
    val server: String,
    var tags: String,
    val title: String,
    var url_m: String,
    val url_s: String,
    val iconserver : String,
    val iconfarm :Int,
    var avatar :String
) :java.io.Serializable