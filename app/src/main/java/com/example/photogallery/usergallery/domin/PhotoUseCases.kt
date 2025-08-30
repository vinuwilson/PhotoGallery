package com.example.photogallery.usergallery.domin

import com.example.photogallery.usergallery.data.model.RecentPhotos
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PhotoUseCases @Inject constructor(
    private val repository: GalleryRepository
) {

    suspend fun getPhotos(): Flow<Result<RecentPhotos>> {
        return repository.getGalleryList().onEach { it ->
            if (it.isSuccess)
                it.getOrNull()?.photos?.photo?.map {
                    if (it.iconserver.toInt() > 0)
                        it.avatar = "http://farm${it.iconfarm}.staticflickr.com/${it.iconserver}/buddyicons/${it.owner}.jpg"
                    else
                        it.avatar = "https://www.flickr.com/images/buddyicon.gif"

                    it.url_m = "https://live.staticflickr.com/${it.server}/${it.id}_${it.secret}.jpg"
                    it.tags = it.tags.replace(" ", ", #")
                }
        }
    }
}