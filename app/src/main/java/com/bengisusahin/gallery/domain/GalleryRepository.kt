package com.bengisusahin.gallery.domain

import android.net.Uri
import com.bengisusahin.gallery.data.ImageLocation

interface GalleryRepository {
    fun getImageLocation(uri: Uri): ImageLocation?
}