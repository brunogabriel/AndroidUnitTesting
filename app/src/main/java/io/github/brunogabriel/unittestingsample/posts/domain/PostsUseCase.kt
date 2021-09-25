package io.github.brunogabriel.unittestingsample.posts.domain

import io.github.brunogabriel.unittestingsample.posts.data.PostsRepository
import io.github.brunogabriel.unittestingsample.posts.domain.mapper.PostMapper
import io.github.brunogabriel.unittestingsample.posts.domain.models.Post
import io.reactivex.Single
import javax.inject.Inject

interface PostsUseCase {
    fun takePosts(): Single<List<Post>>
}

class PostsUseCaseImpl @Inject constructor(
    private val repository: PostsRepository
) : PostsUseCase {
    override fun takePosts(): Single<List<Post>> = repository.getPosts()
        .map { response ->
            response.map { PostMapper.toPost(it) }
        }
}