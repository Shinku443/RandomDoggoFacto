package com.example.randomdoggofacto

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.randomdoggofacto.ui.RandomDogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun RandomDog(
    navController: NavController,
    viewModel: RandomDogViewModel = hiltViewModel()
) {//this will display a random dog with name, img, and facts
    //Name
    //Img
    //Facts

    //Button to get new dog


}