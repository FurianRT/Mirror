package com.furianrt.repository.mappers.domain

import com.furianrt.domain.entities.Entry
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbEntry
import com.furianrt.storage.database.entities.relations.DbFullEntry
import javax.inject.Inject

internal class EntryDomainMapper @Inject constructor(
    private val moodDomainMapper: MoodDomainMapper,
    private val imageDomainMapper: ImageDomainMapper,
    private val occupationDomainMapper: OccupationDomainMapper
) : BaseMapper<Entry, DbFullEntry>() {

    override suspend fun map(from: Entry) = DbFullEntry(
        DbEntry(from.id, from.note, from.timestamp, from.updatedAt, from.createdAt),
        moodDomainMapper.map(from.mood),
        from.occupations.map { occupationDomainMapper.map(it) },
        from.images.map { imageDomainMapper.map(it) }
    )
}