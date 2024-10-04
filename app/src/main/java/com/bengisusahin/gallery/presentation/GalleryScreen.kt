package com.bengisusahin.gallery.presentation

import android.Manifest
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.exifinterface.media.ExifInterface
import coil.compose.rememberAsyncImagePainter
import com.bengisusahin.gallery.data.ImageLocation
import java.io.InputStream

@Composable
fun GalleryScreen() {
    val context = LocalContext.current
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var imageLocation by remember { mutableStateOf<ImageLocation?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
        uri?.let {
            imageLocation = extractLocationFromImage(context.contentResolver.openInputStream(it))
        }
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            imagePickerLauncher.launch("image/*")
        } else {
            Toast.makeText(
                context,
                "Gallery permission is required to select an image.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun checkAndRequestPermission() {
        val permission = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }

        if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PermissionChecker.PERMISSION_GRANTED
        ) {
            imagePickerLauncher.launch("image/*")
        } else {
            permissionLauncher.launch(permission)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { checkAndRequestPermission() }) {
            Text(text = "Select Image")
        }

        Spacer(modifier = Modifier.height(16.dp))

        selectedImageUri?.let {
            Image(
                painter = rememberAsyncImagePainter(selectedImageUri),
                contentDescription = "Selected Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            imageLocation?.let { location ->
                Text(text = "Location: Lat ${location.latitude}, Long ${location.longitude}")
            } ?: run {
                Text(text = "No location info available.")
            }
        } ?: run {
            Text(text = "Select an image to view it.")
        }
    }
}
fun extractLocationFromImage(inputStream: InputStream?): ImageLocation? {
    inputStream?.use {
        val exif = ExifInterface(it)

        val latitudeString = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE)
        val latitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF)
        val longitudeString = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE)
        val longitudeRef = exif.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF)

        val latitude = latitudeString?.toDoubleOrNull()
        val longitude = longitudeString?.toDoubleOrNull()

        if (latitude != null && longitude != null && latitudeRef != null && longitudeRef != null) {
            val lat = if (latitudeRef == "N") latitude else -latitude
            val lon = if (longitudeRef == "E") longitude else -longitude
            return ImageLocation(lat, lon)
        } else {
            Log.d("GalleryScreen", "No valid GPS data found in EXIF: " +
                    "latitudeString=$latitudeString, longitudeString=$longitudeString")
        }
    }
    return null
}