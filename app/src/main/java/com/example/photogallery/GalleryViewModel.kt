package com.example.photogallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.onEach

class GalleryViewModel(
    private val repository: GalleryRepository
) :ViewModel() {

    val galleryList = liveData {
        emitSource(repository.getGalleryList().onEach { it ->
            it.getOrNull()!!.photos.photo.map {
                if(it.iconserver.toInt() > 0 )
                    it.avatar = "http://farm${it.iconfarm}.staticflickr.com/${it.iconserver}/buddyicons/${it.owner}.jpg"
                else
                    it.avatar ="https://www.flickr.com/images/buddyicon.gif"

                it.url_m = "https://live.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
                it.tags = it.tags.replace(" ", ", ")
            }
        }.asLiveData())
    }

}
