package com.example.youtubeapi.data.model.repository
import com.example.youtubeapi.data.YouTubeApiService
import com.example.youtubeapi.data.BaseRepository
import com.example.youtubeapi.data.model.PlaylistsModel
import com.example.youtubeapi.ui.Resource

class PlaylistsRepository(
    private val api: YouTubeApiService
): BaseRepository() {

    suspend fun fetchPlaylists(): Resource<PlaylistsModel> {
        return try {
            val response = api.fetchPlaylists()
            if (response.isSuccessful) {
                val playlistsModel = response.body()
                playlistsModel?.let {
                    Resource.Success(it)
                } ?: Resource.Error("Failed to fetch playlists")
            } else {
                Resource.Error("Failed to fetch playlists")
            }
        } catch (e: Exception) {
            Resource.Error(e.localizedMessage ?: "Unknown error!")
        }
    }
}
