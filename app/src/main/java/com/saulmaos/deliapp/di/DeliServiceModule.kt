package com.saulmaos.deliapp.di

import com.saulmaos.deliapp.data.api.DELI_BASE_URL
import com.saulmaos.deliapp.data.api.DeliService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
class DeliServiceModule {
    @Provides
    fun provideNetworkService(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().apply {
            baseUrl(DELI_BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }

    @Provides
    fun provideDebankService(networkService: Retrofit): DeliService =
        networkService.create(DeliService::class.java)
}