package com.example.randomdoggofacto.data.response

data class DogFactItem(
    val breeds: List<Breed>,
    val height: Int,
    val id: String,
    val url: String,
    val width: Int
)