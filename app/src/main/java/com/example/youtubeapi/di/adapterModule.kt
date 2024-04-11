package com.example.youtubeapi.di

import com.example.youtubeapi.ui.PlaylistsAdapter
import org.koin.dsl.module


val adapterModule = module {
    single { PlaylistsAdapter() }
}
