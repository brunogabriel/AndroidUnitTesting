package io.github.brunogabriel.unittestingsample.posts.data

import io.github.brunogabriel.unittestingsample.posts.data.models.PostResponse
import io.github.brunogabriel.unittestingsample.posts.data.service.PostService
import io.reactivex.Single
import javax.inject.Inject

interface PostsRepository {
    fun getPosts(): Single<List<PostResponse>>
}

class PostsRepositoryImpl @Inject constructor(
    private val service: PostService
) : PostsRepository {
    override fun getPosts(): Single<List<PostResponse>> = service.getPosts()
}