package io.github.brunogabriel.unittestingsample.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AppBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ChuckLoggerInterceptor