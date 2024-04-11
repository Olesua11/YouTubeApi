package com.example.youtubeapi.di

import com.example.youtubeapi.data.YouTubeApiService
import com.example.youtubeapi.data.model.repository.PlaylistItemRepository
import com.example.youtubeapi.data.model.repository.PlaylistsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { providePlaylistsRepository(get()) }
    single { providePlaylistItemRepository(get()) }
}

fun providePlaylistsRepository(api: YouTubeApiService) = PlaylistsRepository(api)

fun providePlaylistItemRepository(api: YouTubeApiService) = PlaylistItemRepository(api)
