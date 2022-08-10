package com.example.randomdoggofacto.di

import com.example.randomdoggofacto.util.Constants
import com.example.randomdoggofacto.util.Constants.BASE_URL
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun provideRepo(
        api: DogApi
    ) = DogRepository(api)


    fun provideApi(): DogApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(DogApi::class.java)

    }
}