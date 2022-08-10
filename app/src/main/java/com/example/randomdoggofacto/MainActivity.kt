package com.example.randomdoggofacto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.randomdoggofacto.ui.RandomDog
import com.example.randomdoggofacto.ui.theme.RandomDoggoFactoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomDoggoFactoTheme {
                // A surface container using the 'background' color from the theme
                /* Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                }*/
                val navController =
                    rememberNavController() //Have to setup nav controller out here otherwise
                //rememberNavController will return a different nav controller inside the composable
                NavHost(
                    navController = navController,
                    startDestination = "random_dog_screen"
                ) {
                    composable("random_dog_screen") {
                        RandomDog(navController = navController)
                    }
                }
            }
        }
    }
}
