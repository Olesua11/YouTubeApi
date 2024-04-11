package com.example.youtubeapi.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.youtubeapi.data.model.repository.PlaylistsRepository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.youtubeapi.data.model.PlaylistsModel
import com.example.youtubeapi.ui.Resource
import kotlinx.coroutines.launch


class PlaylistsViewModel(private val repository: PlaylistsRepository) : ViewModel() {

    private val _playlists = MutableLiveData<Resource<PlaylistsModel>>()
    val playlists: LiveData<Resource<PlaylistsModel>> get() = _playlists

    fun fetchPlaylists() {
        viewModelScope.launch {
            _playlists.value = Resource.Loading()
            try {
                val playlistsResult = repository.fetchPlaylists()
                _playlists.value = playlistsResult
            } catch (e: Exception) {
                _playlists.value = Resource.Error(e.localizedMessage ?: "Unknown error!")
            }
        }
    }
}







