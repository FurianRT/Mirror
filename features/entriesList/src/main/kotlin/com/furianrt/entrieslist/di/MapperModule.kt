package com.furianrt.entrieslist.di

import com.furianrt.domain.entities.Entry
import com.furianrt.entrieslist.entities.EntriesListItem
import com.furianrt.entrieslist.mappers.EntriesListMapper
import com.furianrt.presentation.mapper.BaseMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
internal interface MapperModule {

    @Binds
    fun entriesListMapper(imp: EntriesListMapper): BaseMapper<List<Entry>, List<EntriesListItem>>
}