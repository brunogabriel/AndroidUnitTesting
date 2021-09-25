package io.github.brunogabriel.unittestingsample.posts.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import io.github.brunogabriel.unittestingsample.posts.domain.PostsUseCase
import io.github.brunogabriel.unittestingsample.posts.domain.models.Post
import io.github.brunogabriel.unittestingsample.posts.presentation.viewmodel.PostsViewModel.Companion.DEFAULT_ERROR_MESSAGE
import io.github.brunogabriel.unittestingsample.shared.rx.AppSchedulers
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PostsViewModelTest {
    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var useCase: PostsUseCase
    private lateinit var viewModel: PostsViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = PostsViewModel(
            useCase = useCase,
            appSchedulers = AppSchedulers(
                Schedulers.trampoline(),
                Schedulers.trampoline(),
                Schedulers.trampoline()
            )
        )
    }

    @Test
    fun `should take posts and display it when call takePosts`() {
        // given
        val posts = listOf<Post>(mockk(), mockk())

        val observerDisplayingView = spyk<Observer<Int>>()
        val displayingViewResults = mutableListOf<Int>()

        val observerPostResult = spyk<Observer<List<Post>>>()
        val postResults = mutableListOf<List<Post>>()

        every { useCase.takePosts() } returns Single.just(posts)

        viewModel.displayingView.observeForever(observerDisplayingView)
        viewModel.postResult.observeForever(observerPostResult)

        // when
        viewModel.takePosts()

        // then
        verify { observerDisplayingView.onChanged(capture(displayingViewResults)) }
        verify { observerPostResult.onChanged(capture(postResults)) }

        assertThat(displayingViewResults).isEqualTo(
            listOf(
                PostsViewModel.DisplayingView.Loading.ordinal,
                PostsViewModel.DisplayingView.Success.ordinal
            )
        )

        assertThat(postResults.size).isEqualTo(1)
        assertThat(postResults.first()).isEqualTo(posts)
    }

    @Test
    fun `should display empty result when call takePosts`() {
        // given
        val posts = emptyList<Post>()

        val observerDisplayingView = spyk<Observer<Int>>()
        val displayingViewResults = mutableListOf<Int>()

        every { useCase.takePosts() } returns Single.just(posts)

        viewModel.displayingView.observeForever(observerDisplayingView)

        // when
        viewModel.takePosts()

        // then
        verify { observerDisplayingView.onChanged(capture(displayingViewResults)) }

        assertThat(displayingViewResults).isEqualTo(
            listOf(
                PostsViewModel.DisplayingView.Loading.ordinal,
                PostsViewModel.DisplayingView.Empty.ordinal
            )
        )
    }

    @Test
    fun `should display error when call takePosts`() {
        // given
        val exceptionMessage = "AnyExceptionMessage"

        val observerDisplayingView = spyk<Observer<Int>>()
        val displayingViewResults = mutableListOf<Int>()

        val observerErrorMessage = spyk<Observer<String>>()
        val errorResults = mutableListOf<String>()

        every { useCase.takePosts() } returns Single.error(Exception(exceptionMessage))

        viewModel.displayingView.observeForever(observerDisplayingView)
        viewModel.errorMessage.observeForever(observerErrorMessage)

        // when
        viewModel.takePosts()

        // then
        verify { observerDisplayingView.onChanged(capture(displayingViewResults)) }
        verify { observerErrorMessage.onChanged(capture(errorResults)) }

        assertThat(displayingViewResults).isEqualTo(
            listOf(
                PostsViewModel.DisplayingView.Loading.ordinal,
                PostsViewModel.DisplayingView.Error.ordinal
            )
        )

        assertThat(errorResults.size).isEqualTo(1)
        assertThat(errorResults.first()).isEqualTo(exceptionMessage)
    }

    @Test
    fun `should display default error when call takePosts with null message`() {
        // given
        val observerDisplayingView = spyk<Observer<Int>>()
        val displayingViewResults = mutableListOf<Int>()

        val observerErrorMessage = spyk<Observer<String>>()
        val errorResults = mutableListOf<String>()

        every { useCase.takePosts() } returns Single.error(Exception())

        viewModel.displayingView.observeForever(observerDisplayingView)
        viewModel.errorMessage.observeForever(observerErrorMessage)

        // when
        viewModel.takePosts()

        // then
        verify { observerDisplayingView.onChanged(capture(displayingViewResults)) }
        verify { observerErrorMessage.onChanged(capture(errorResults)) }

        assertThat(displayingViewResults).isEqualTo(
            listOf(
                PostsViewModel.DisplayingView.Loading.ordinal,
                PostsViewModel.DisplayingView.Error.ordinal
            )
        )

        assertThat(errorResults.size).isEqualTo(1)
        assertThat(errorResults.first()).isEqualTo(DEFAULT_ERROR_MESSAGE)
    }
}