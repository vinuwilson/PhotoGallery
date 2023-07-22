package com.example.photogallery.usergallery.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.photogallery.usergallery.network.GalleryRepository
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
    private val repository: GalleryRepository
) :ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val galleryList = liveData {
        loader.postValue(true)
        emitSource(repository.getGalleryList().onEach { it ->
            if(it.isSuccess)
                it.getOrNull()!!.photos.photo.map {
                    if (it.iconserver.toInt() > 0)
                        it.avatar =
                            "http://farm${it.iconfarm}.staticflickr.com/${it.iconserver}/buddyicons/${it.owner}.jpg"
                    else
                        it.avatar = "https://www.flickr.com/images/buddyicon.gif"

                    it.url_m =
                        "https://live.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
                    it.tags = it.tags.replace(" ", ", #")
                }
            loader.postValue(false)
        }.asLiveData())
    }

}
