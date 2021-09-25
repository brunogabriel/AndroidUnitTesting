package io.github.brunogabriel.unittestingsample.posts.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.brunogabriel.unittestingsample.posts.domain.PostsUseCase
import io.github.brunogabriel.unittestingsample.posts.domain.models.Post
import io.github.brunogabriel.unittestingsample.shared.rx.AppSchedulers
import io.github.brunogabriel.unittestingsample.shared.viewmodel.BaseViewModel
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val useCase: PostsUseCase,
    private val appSchedulers: AppSchedulers
) : BaseViewModel() {

    private val _displayingView = MutableLiveData<Int>()
    val displayingView: LiveData<Int>
        get() = _displayingView

    private val _postsResult = MutableLiveData<List<Post>>()
    val postResult: LiveData<List<Post>>
        get() = _postsResult

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage

    fun takePosts() {
        compositeDisposable + useCase.takePosts()
            .subscribeOn(appSchedulers.io)
            .observeOn(appSchedulers.main)
            .doOnSubscribe { _displayingView.value = DisplayingView.Loading.ordinal }
            .subscribeBy(onError = {
                _displayingView.value = DisplayingView.Error.ordinal
                _errorMessage.value = it.message ?: DEFAULT_ERROR_MESSAGE
            }, onSuccess = { result ->
                if (result.isNotEmpty()) {
                    _postsResult.value = result
                    _displayingView.value = DisplayingView.Success.ordinal
                } else {
                    _displayingView.value = DisplayingView.Empty.ordinal
                }
            })
    }

    internal enum class DisplayingView {
        Loading,
        Success,
        Empty,
        Error
    }

    companion object {
        const val DEFAULT_ERROR_MESSAGE = "No error message found"
    }
}