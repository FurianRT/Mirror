package com.furianrt.domain.di

import com.furianrt.domain.base.BaseDispatcherProvider
import com.furianrt.domain.dispatchers.DispatcherProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DomainUtilsModule {

    @Binds
    fun dispatcherProvider(imp: DispatcherProvider): BaseDispatcherProvider
}