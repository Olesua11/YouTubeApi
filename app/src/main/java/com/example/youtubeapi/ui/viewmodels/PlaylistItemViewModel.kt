package com.example.youtubeapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.model.repository.PlaylistItemRepository

class PlaylistItemViewModel(
    private val repository: PlaylistItemRepository
): ViewModel() {
}