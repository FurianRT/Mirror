package com.furianrt.entrieslist.di

import androidx.fragment.app.Fragment
import com.furianrt.entrieslist.EntriesListAdapter.EntriesListListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
internal object EntriesListModule {

    @Provides
    fun provideEntriesListListener(fragment: Fragment) = fragment as EntriesListListener
}