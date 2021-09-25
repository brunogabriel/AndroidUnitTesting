package io.github.brunogabriel.unittestingsample.posts.data.service

import io.github.brunogabriel.unittestingsample.posts.data.models.PostResponse
import io.reactivex.Single
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    fun getPosts(): Single<List<PostResponse>>
}