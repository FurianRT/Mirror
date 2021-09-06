package com.furianrt.domain.base

import kotlinx.coroutines.CoroutineDispatcher

abstract class BaseDispatcherProvider {
    abstract fun main(): CoroutineDispatcher
    abstract fun background(): CoroutineDispatcher
}