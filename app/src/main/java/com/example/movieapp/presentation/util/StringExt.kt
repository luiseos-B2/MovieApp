package com.example.movieapp.presentation.util

fun String.createImageUrl(): String {
    return "https://image.tmdb.org/t/p/w500//$this"
}