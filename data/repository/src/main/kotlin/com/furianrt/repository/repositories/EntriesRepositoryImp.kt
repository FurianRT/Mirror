package com.furianrt.repository.repositories

import com.furianrt.domain.entities.EntriesSorting
import com.furianrt.domain.entities.Entry
import com.furianrt.domain.repositories.EntriesRepository
import com.furianrt.repository.mappers.database.EntryDbMapper
import com.furianrt.repository.mappers.domain.EntryDomainMapper
import com.furianrt.storage.database.dao.*
import com.furianrt.storage.database.entities.link.DbEntryMoodLink
import com.furianrt.storage.database.entities.link.DbEntryOccupationLink
import com.furianrt.storage.database.entities.link.DbMoodIconLink
import com.furianrt.storage.database.entities.link.DbOccupationIconLink
import com.furianrt.storage.preferences.PreferencesManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class EntriesRepositoryImp @Inject constructor(
    private val entryDao: EntryDao,
    private val moodDao: MoodDao,
    private val imageDao: ImageDao,
    private val occupationDao: OccupationDao,
    private val moodIconLinkDao: MoodIconLinkDao,
    private val occupationIconLinkDao: OccupationIconLinkDao,
    private val entryMoodLinkDao: EntryMoodLinkDao,
    private val entryOccupationLinkDao: EntryOccupationLinkDao,
    private val entryDbMapper: EntryDbMapper,
    private val entryDomainMapper: EntryDomainMapper,
    private val preferencesManager: PreferencesManager
) : EntriesRepository {

    override suspend fun insertEntry(entry: Entry) {
        entryDao.transaction { insertEntryInternal(entry) }
    }

    override suspend fun insertEntries(entries: List<Entry>) {
        entryDao.transaction { entries.forEach { insertEntryInternal(it) } }
    }

    private suspend fun insertEntryInternal(entry: Entry) {
        val dbFullEntry = entryDomainMapper.map(entry)
        moodDao.insert(dbFullEntry.mood.mood)
        occupationDao.insert(dbFullEntry.occupations.map { it.occupation })
        entryDao.insert(dbFullEntry.entry)
        imageDao.insert(dbFullEntry.images)
        entryMoodLinkDao.insert(DbEntryMoodLink(dbFullEntry.entry.id, dbFullEntry.mood.mood.id))
        moodIconLinkDao.insert(
            DbMoodIconLink(
                dbFullEntry.mood.mood.id,
                dbFullEntry.mood.icon.name
            )
        )
        dbFullEntry.occupations.forEach { occupation ->
            entryOccupationLinkDao.insert(
                DbEntryOccupationLink(
                    dbFullEntry.entry.id,
                    occupation.occupation.name
                )
            )
            occupationIconLinkDao.insert(
                DbOccupationIconLink(
                    occupation.occupation.name,
                    occupation.icon.name
                )
            )
        }
    }

    override fun getAllEntries(): Flow<List<Entry>> =
        entryDao.getAllEntries().map { entries -> entries.map { entryDbMapper.map(it) } }

    override fun getEntriesSorting(): Flow<EntriesSorting> =
        preferencesManager.getEntriesSorting().map { sorting ->
            when (sorting) {
                PreferencesManager.SORTING_ASC -> EntriesSorting.ASC
                PreferencesManager.SORTING_DESC -> EntriesSorting.DESC
                else -> throw IllegalArgumentException()
            }
        }

    override suspend fun setEntriesSorting(sorting: EntriesSorting) {
        preferencesManager.setEntriesSorting(
            when (sorting) {
                EntriesSorting.ASC -> PreferencesManager.SORTING_ASC
                EntriesSorting.DESC -> PreferencesManager.SORTING_DESC
            }
        )
    }
}