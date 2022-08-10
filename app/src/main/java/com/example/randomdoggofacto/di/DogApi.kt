package com.example.randomdoggofacto.di

import com.example.randomdoggofacto.data.response.DogFactItem
import retrofit2.http.GET

interface DogApi {

    @GET("/search")
    suspend fun getDogItem(): DogFactItem
}