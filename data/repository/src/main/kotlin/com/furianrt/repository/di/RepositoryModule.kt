package com.furianrt.repository.di

import com.furianrt.domain.repositories.EntriesRepository
import com.furianrt.domain.repositories.MoodRepository
import com.furianrt.repository.repositories.EntriesRepositoryImp
import com.furianrt.repository.repositories.MoodRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    fun entriesRepository(imp: EntriesRepositoryImp): EntriesRepository

    @Binds
    fun moodRepository(imp: MoodRepositoryImp): MoodRepository
}