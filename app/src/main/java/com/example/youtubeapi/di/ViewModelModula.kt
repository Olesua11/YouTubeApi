package com.example.youtubeapi.di

import com.example.youtubeapi.data.model.repository.PlaylistItemRepository
import com.example.youtubeapi.data.model.repository.PlaylistsRepository
import com.example.youtubeapi.ui.viewmodels.PlaylistItemViewModel
import com.example.youtubeapi.ui.viewmodels.PlaylistsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { providePlaylistsViewModel(get()) }
    viewModel { providePlaylistItemViewModel(get()) }
}

fun providePlaylistsViewModel(repository: PlaylistsRepository) = PlaylistsViewModel(repository)

fun providePlaylistItemViewModel(repository: PlaylistItemRepository) = PlaylistItemViewModel(repository)
