package io.github.brunogabriel.unittestingsample.posts.domain

import io.github.brunogabriel.unittestingsample.posts.data.PostsRepository
import io.github.brunogabriel.unittestingsample.posts.data.models.PostResponse
import io.github.brunogabriel.unittestingsample.posts.domain.mapper.PostMapper
import io.github.brunogabriel.unittestingsample.posts.domain.models.Post
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

class PostsUseCaseTest {

    @MockK
    private lateinit var repository: PostsRepository

    private lateinit var useCase: PostsUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        useCase = PostsUseCaseImpl(repository)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `should take posts when call takePosts`() {
        // given
        val postResponse = mockk<PostResponse>()
        val post = mockk<Post>()

        mockkObject(PostMapper)
        every { repository.getPosts() } returns Single.just(listOf(postResponse))
        every { PostMapper.toPost(postResponse) } returns post

        // when
        val result = useCase.takePosts()

        // then
        result.test()
            .assertResult(listOf(post))
            .dispose()
    }
}