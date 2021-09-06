package com.furianrt.domain.repositories

import com.furianrt.domain.entities.EntriesSorting
import com.furianrt.domain.entities.Entry
import kotlinx.coroutines.flow.Flow

interface EntriesRepository {
    suspend fun insertEntry(entry: Entry)
    suspend fun insertEntries(entries: List<Entry>)
    fun getAllEntries(): Flow<List<Entry>>
    suspend fun setEntriesSorting(sorting: EntriesSorting)
    fun getEntriesSorting(): Flow<EntriesSorting>
}