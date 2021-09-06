package com.furianrt.domain.dispatchers

import com.furianrt.domain.base.BaseDispatcherProvider
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DispatcherProvider @Inject constructor() : BaseDispatcherProvider() {
    override fun main() = Dispatchers.Main.immediate
    override fun background() = Dispatchers.IO
}