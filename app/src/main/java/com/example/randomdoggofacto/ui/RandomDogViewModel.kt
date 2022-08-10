package com.example.randomdoggofacto.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggofacto.data.response.DogFactItem
import com.example.randomdoggofacto.di.DogRepository
import com.example.randomdoggofacto.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class RandomDogViewModel @Inject constructor(
    private val dogRepository: DogRepository,
) : ViewModel() {
    var randomDogFact = mutableStateOf<DogFactItem?>(null)
    var isLoading = mutableStateOf(true)
    var errorOccurred = mutableStateOf(false)

    fun getRandomDogFact() {
        viewModelScope.launch {
            var result = dogRepository.getDogItem()
            when(result){
                is Resource.Error -> {
                    errorOccurred.value = true
                }
                is Resource.Loading -> {
                    isLoading.value = true
                    randomDogFact.value = result.data
                }
                is Resource.Success -> {
                    isLoading.value = false
                }
            }
        }
    }

}