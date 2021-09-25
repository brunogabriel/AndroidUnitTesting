package io.github.brunogabriel.unittestingsample.shared.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel : ViewModel() {
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()

    operator fun CompositeDisposable.plus(disposable: Disposable) {
        this.add(disposable)
    }
}