package com.bengisusahin.gallery.data

import android.content.ContentResolver
import android.net.Uri
import androidx.exifinterface.media.ExifInterface
import com.bengisusahin.gallery.domain.GalleryRepository
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GalleryRepositoryImpl @Inject constructor(
    private val contentResolver: ContentResolver
) : GalleryRepository {
    override fun getImageLocation(uri: Uri): ImageLocation? {
        val inputStream: InputStream? = contentResolver.openInputStream(uri)
        return extractLocationFromImage(inputStream)
    }

    private fun extractLocationFromImage(inputStream: InputStream?): ImageLocation? {
        inputStream?.use {
            val exif = ExifInterface(it)
            val latitudeString = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)
            val latitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF)
            val longitudeString = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)
            val longitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF)
            val dateTime = exif.getAttribute(ExifInterface.TAG_DATETIME)

            if (latitudeString != null && longitudeString != null && latitudeRef != null && longitudeRef != null) {
                val latitude = dmsToDecimal(latitudeString, latitudeRef)
                val longitude = dmsToDecimal(longitudeString, longitudeRef)
                return ImageLocation(latitude, longitude, dateTime)
            }
        }
        return null
    }

    private fun dmsToDecimal(dms: String, ref: String): Double {
        val parts = dms.split(",")
        val degrees = parts[0].split("/").let { it[0].toDouble() / it[1].toDouble() }
        val minutes = parts[1].split("/").let { it[0].toDouble() / it[1].toDouble() }
        val seconds = parts[2].split("/").let { it[0].toDouble() / it[1].toDouble() }
        val decimal = degrees + (minutes / 60) + (seconds / 3600)
        return if (ref == "S" || ref == "W") -decimal else decimal
    }
}