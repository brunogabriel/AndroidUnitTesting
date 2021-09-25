package io.github.brunogabriel.unittestingsample.shared.rx

import io.reactivex.Scheduler
import javax.inject.Inject

class AppSchedulers @Inject constructor(
    val io: Scheduler,
    val main: Scheduler,
    val computation: Scheduler
)