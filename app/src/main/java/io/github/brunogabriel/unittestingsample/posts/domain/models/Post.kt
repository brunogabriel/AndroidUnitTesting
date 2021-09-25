package io.github.brunogabriel.unittestingsample.posts.domain.models

data class Post(
    val id: String,
    val userId: String,
    val title: String,
    val body: String
)