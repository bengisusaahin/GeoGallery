package com.bengisusahin.gallery.presentation

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bengisusahin.gallery.data.ImageLocation
import com.bengisusahin.gallery.domain.GetImageMetaDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getImageMetaDataUseCase: GetImageMetaDataUseCase
) : ViewModel() {
    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> get() = _selectedImageUri

    private val _imageLocation = MutableStateFlow<ImageLocation?>(null)
    val imageLocation: StateFlow<ImageLocation?> get() = _imageLocation

    fun selectImage(uri: Uri) {
        _selectedImageUri.value = uri
        viewModelScope.launch {
            _imageLocation.value = getImageMetaDataUseCase(uri)
        }
    }
}