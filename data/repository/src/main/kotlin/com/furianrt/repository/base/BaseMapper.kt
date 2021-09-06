package com.furianrt.repository.base

internal abstract class BaseMapper<F, T> {

    abstract suspend fun map(from: F): T
}