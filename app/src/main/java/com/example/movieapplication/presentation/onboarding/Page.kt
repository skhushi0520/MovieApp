package com.example.movieapplication.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.movieapplication.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val image: Int
)

val page = listOf(
    Page(
        title = "Movie",
        description = "hello",
        image = R.drawable.onboarding1
    ),
    Page(
        title = "Movie",
        description = "hi",
        image = R.drawable.onboarding2
    ),
    Page(
        title = "Movie",
        description = "hi",
        image = R.drawable.onboarding3
    )
)
