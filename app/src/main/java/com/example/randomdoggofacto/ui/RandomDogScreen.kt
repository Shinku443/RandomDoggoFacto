package com.example.randomdoggofacto.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.coil.R
import timber.log.Timber

@Composable
fun RandomDog(
    navController: NavController, //eventually can goto specific screens
    viewModel: RandomDogViewModel = hiltViewModel()
) {//this will display a random dog with name, img, and facts
    val dogFact by remember { viewModel.randomDogFact }
    val isLoading by remember { viewModel.isLoading }
    //Name
    //Img
    //Facts
    /*val dogInfo = produceState<Resource<List<DogFactItem>>>(initialValue = Resource.Loading()) {
        value = viewModel.getRandomDogFact()
    }.value*/
    Surface(
        Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
    ) {
//History/Cache/Bookmark
        Column(verticalArrangement = Arrangement.Center) {
            if (isLoading) {
                Timber.e("loading")
                CircularProgressIndicator()
            } else {
                Timber.e("done loading::: $dogFact")
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(dogFact[0].url)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.notify_panel_notification_icon_bg),
                    contentDescription = dogFact[0].id,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        //.clip(CircleShape)
                        .height(
                            when (dogFact[0].height < 400) {
                                true -> dogFact[0].height.dp
                                false -> 400.dp
                            }
                        )
                        .width(
                            when (dogFact[0].width < 400) {
                                true -> dogFact[0].width.dp
                                false -> 400.dp
                            }
                        )
                )
                Text(//do breeds instead?
                    text = "Name: ${
                        when (dogFact.isNotEmpty()) {
                            true -> when (dogFact[0].breeds.isNotEmpty()) {
                                true -> dogFact[0].breeds[0].name
                                false -> dogFact[0].id
                            }
                            false -> ""
                        }
                    }", modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Bred for: ${
                        when (dogFact[0].breeds.isNotEmpty()) {
                            true -> dogFact[0].breeds[0].bred_for
                            false -> ""
                        }
                    }",
                    Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Breed Group: ${
                        when (dogFact[0].breeds.isNotEmpty()) {
                            true -> dogFact[0].breeds[0].breed_group
                            false -> ""
                        }
                    }", Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text( //put in a system for metric vs imperial (flip a switch or setting)
                    text = "Height (cm): ${
                        when (dogFact[0].breeds.isNotEmpty()) {
                            true -> dogFact[0].breeds[0].height.metric
                            false -> ""
                        }
                    } and Weight (kg): ${
                        when (dogFact[0].breeds.isNotEmpty()) {
                            true -> dogFact[0].breeds[0].weight.metric
                            false -> ""
                        }
                    }", Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Life Span: ${
                        when (dogFact[0].breeds.isNotEmpty()) {
                            true -> dogFact[0].breeds[0].life_span
                            false -> ""
                        }
                    }", Modifier.align(CenterHorizontally)
                )
            }


            //Button to get new dog
            NewDogButton(Modifier.align(CenterHorizontally)) {
                viewModel.getRandomDogFact()
            }
        }
    }
}

@Composable
fun NewDogButton(
    modifier: Modifier,
    onRetry: () -> Unit
) {
    Button(
        onClick = { onRetry() },
        modifier = modifier
    ) {
        Text(text = "New Dog")
    }
}
