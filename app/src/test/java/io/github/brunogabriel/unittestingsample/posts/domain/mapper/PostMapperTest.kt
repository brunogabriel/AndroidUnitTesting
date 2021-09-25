package io.github.brunogabriel.unittestingsample.posts.domain.mapper

import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.unittestingsample.posts.data.models.PostResponse
import io.github.brunogabriel.unittestingsample.posts.domain.models.Post
import org.junit.Test

class PostMapperTest {
    @Test
    fun `should map PostResponse to Post`() {
        // given
        val postResponse = PostResponse(
            id = 1_000L,
            userId = 2_000L,
            title = "anyTitle",
            body = "anyBody"
        )

        // when
        val result = PostMapper.toPost(postResponse)

        // then
        assertThat(result).isEqualTo(
            Post(
                id = "1000",
                userId = "2000",
                title = "anyTitle",
                body = "anyBody"
            )
        )
    }
}