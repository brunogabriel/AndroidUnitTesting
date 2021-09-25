package io.github.brunogabriel.unittestingsample.posts.domain.mapper

import io.github.brunogabriel.unittestingsample.posts.data.models.PostResponse
import io.github.brunogabriel.unittestingsample.posts.domain.models.Post

object PostMapper {
    fun toPost(response: PostResponse) = Post(
        id = response.id.toString(),
        userId = response.userId.toString(),
        title = response.title,
        body = response.body
    )
}