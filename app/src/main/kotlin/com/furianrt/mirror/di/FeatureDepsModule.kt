package com.furianrt.mirror.di

import com.furianrt.entrieslist.api.EntryListDeps
import com.furianrt.mirror.deps.EntryListDepsImp
import com.furianrt.mirror.deps.QuotesDepsImp
import com.furianrt.mirror.deps.SettingsDepsImp
import com.furianrt.mirror.deps.StatisticsDepsImp
import com.furianrt.quotes.api.QuotesDeps
import com.furianrt.settings.api.SettingsDeps
import com.furianrt.statistics.api.StatisticsDeps
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface FeatureDepsModule {

    @Binds
    fun entryListDeps(imp: EntryListDepsImp): EntryListDeps

    @Binds
    fun settingsDeps(imp: SettingsDepsImp): SettingsDeps

    @Binds
    fun statisticsDeps(imp: StatisticsDepsImp): StatisticsDeps

    @Binds
    fun quotesDeps(imp: QuotesDepsImp): QuotesDeps
}