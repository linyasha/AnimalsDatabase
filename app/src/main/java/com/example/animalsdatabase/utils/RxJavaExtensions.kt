package com.example.animalsdatabase.utils

import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.Single
import org.reactivestreams.Publisher

fun <T> Observable<T>.toLiveData() = LiveDataReactiveStreams.fromPublisher(this.toFlowable(BackpressureStrategy.LATEST))
