package com.furianrt.repository.mappers.database

import com.furianrt.domain.entities.Entry
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.relations.DbFullEntry
import javax.inject.Inject

internal class EntryDbMapper @Inject constructor(
    private val moodDbMapper: MoodDbMapper,
    private val occupationDbMapper: OccupationDbMapper,
    private val imageDbMapper: ImageDbMapper
) : BaseMapper<DbFullEntry, Entry>() {

    override suspend fun map(from: DbFullEntry) = Entry(
        from.entry.id,
        from.entry.note,
        from.entry.timestamp,
        from.entry.updatedAt,
        from.entry.createdAt,
        moodDbMapper.map(from.mood),
        from.occupations.map { occupationDbMapper.map(it) }.toSet(),
        from.images.map { imageDbMapper.map(it) }
    )
}