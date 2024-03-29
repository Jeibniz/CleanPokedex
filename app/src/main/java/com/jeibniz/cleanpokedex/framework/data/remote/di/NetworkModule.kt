package com.jeibniz.cleanpokedex.framework.data.remote.di

import com.jeibniz.cleanpokedex.framework.data.pokemon.remote.NetworkConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @BaseUrl
    fun provideBaseUrl() = NetworkConstants.BASE_URL

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BASIC)
            }
        )
        .connectTimeout(NetworkConstants.CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(NetworkConstants.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideGsonConvertor(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideRetrofit(
        @BaseUrl url: String,
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()
}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BaseUrl
