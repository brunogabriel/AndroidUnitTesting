package io.github.brunogabriel.unittestingsample.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.brunogabriel.unittestingsample.shared.rx.AppSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchedulersModule {
    @Provides
    @Singleton
    fun provideAppSchedulers(): AppSchedulers = AppSchedulers(
        io = Schedulers.io(),
        main = AndroidSchedulers.mainThread(),
        computation = Schedulers.computation()
    )
}