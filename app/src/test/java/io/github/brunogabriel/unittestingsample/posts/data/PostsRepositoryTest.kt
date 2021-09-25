package io.github.brunogabriel.unittestingsample.posts.data

import io.github.brunogabriel.unittestingsample.posts.data.models.PostResponse
import io.github.brunogabriel.unittestingsample.posts.data.service.PostService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

class PostsRepositoryTest() {
    @MockK
    private lateinit var service: PostService

    private lateinit var repository: PostsRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repository = PostsRepositoryImpl(service)
    }

    @Test
    fun `should get posts when call getPosts`() {
        // given
        val posts = listOf<PostResponse>(mockk(), mockk())
        every { repository.getPosts() } returns Single.just(posts)

        // when
        val result = repository.getPosts()

        // then
        result.test()
            .assertResult(posts)
            .dispose()
    }
}