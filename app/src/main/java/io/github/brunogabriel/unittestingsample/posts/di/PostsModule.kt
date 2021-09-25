package io.github.brunogabriel.unittestingsample.posts.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import io.github.brunogabriel.unittestingsample.posts.data.PostsRepository
import io.github.brunogabriel.unittestingsample.posts.data.PostsRepositoryImpl
import io.github.brunogabriel.unittestingsample.posts.data.service.PostService
import io.github.brunogabriel.unittestingsample.posts.domain.PostsUseCase
import io.github.brunogabriel.unittestingsample.posts.domain.PostsUseCaseImpl
import retrofit2.Retrofit

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class PostsModule {
    @Binds
    abstract fun bindRepository(repository: PostsRepositoryImpl): PostsRepository

    @Binds
    abstract fun bindUseCase(useCase: PostsUseCaseImpl): PostsUseCase

    companion object {
        @Provides
        fun provideService(retrofit: Retrofit): PostService =
            retrofit.create(PostService::class.java)
    }
}