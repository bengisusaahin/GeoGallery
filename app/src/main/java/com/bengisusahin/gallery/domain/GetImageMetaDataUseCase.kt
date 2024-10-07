package com.bengisusahin.gallery.domain

import android.net.Uri
import com.bengisusahin.gallery.data.ImageLocation
import javax.inject.Inject

class GetImageMetaDataUseCase @Inject constructor(
    private val galleryRepository: GalleryRepository
) {
    operator fun invoke(uri: Uri): ImageLocation? {
        return galleryRepository.getImageLocation(uri)
    }

}