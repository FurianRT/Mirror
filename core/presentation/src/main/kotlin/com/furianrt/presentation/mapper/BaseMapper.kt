package com.furianrt.presentation.mapper

abstract class BaseMapper<F, T> {

    abstract fun map(from: F): T
}