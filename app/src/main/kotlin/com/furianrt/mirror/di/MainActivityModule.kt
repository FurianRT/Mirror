package com.furianrt.mirror.di

import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
internal object MainActivityModule {

    @Provides
    @MainFragmentManager
    fun provideMainFragmentManager(activity: FragmentActivity) = activity.supportFragmentManager
}