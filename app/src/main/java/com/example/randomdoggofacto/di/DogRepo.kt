package com.example.randomdoggofacto.di

import com.example.randomdoggofacto.data.response.DogFactItem
import com.example.randomdoggofacto.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class DogRepository @Inject constructor(
    private val api: DogApi,
) {
    suspend fun getDogItem(): Resource<DogFactItem> {
        val response = try {
            api.getDogItem()
        } catch (e: Exception) {
            return Resource.Error("Error: $e")
        }
        return Resource.Success(response)
    }

}