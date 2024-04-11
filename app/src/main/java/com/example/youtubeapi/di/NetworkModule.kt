package com.example.youtubeapi.di

import com.example.youtubeapi.data.YouTubeApiService
import com.example.youtubeapi.data.network.NetworkService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

val networkModule = module {
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideYouTubeApiService(get()) }
}

fun provideLoggingInterceptor(): HttpLoggingInterceptor = NetworkService.createLoggingInterceptor()

fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient = NetworkService.createOkHttpClient(interceptor)

fun provideRetrofit(client: OkHttpClient): Retrofit = NetworkService.createRetrofit(client)

fun provideYouTubeApiService(retrofit: Retrofit): YouTubeApiService = retrofit.create(YouTubeApiService::class.java)
