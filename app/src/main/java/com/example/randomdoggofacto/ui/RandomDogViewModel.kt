package com.example.randomdoggofacto.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdoggofacto.data.response.DogFactItem
import com.example.randomdoggofacto.di.DogRepository
import com.example.randomdoggofacto.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RandomDogViewModel @Inject constructor(
    private val dogRepository: DogRepository,
) : ViewModel() {
    var randomDogFact = mutableStateOf<List<DogFactItem>>(listOf())
    var isLoading = mutableStateOf(true)
    var errorOccurred = mutableStateOf(false)

    init {
        getRandomDogFact()
    }

    fun getRandomDogFact() {
        Timber.e("getting new fact")
        viewModelScope.launch {
            var result = dogRepository.getDogItem()
            Timber.e("Result: ${result.data} and msg: ${result.message}")
            when (result) {
                is Resource.Error -> {
                    errorOccurred.value = true
                }
                is Resource.Loading -> {
                    isLoading.value = true

                }
                is Resource.Success -> {
                    val entries = result.data?.mapIndexed { _, item ->
                        DogFactItem(
                            breeds = item.breeds,
                            height = item.height,
                            id = item.id,
                            url = item.url,
                            width = item.width
                        )

                    }
                    Timber.e("entries::: $entries")

                    if(!entries.isNullOrEmpty()) {
                        randomDogFact.value = entries
                        Timber.e("new fact::: $randomDogFact")
                    }
                    isLoading.value = false
                }
            }
        }
    }

}