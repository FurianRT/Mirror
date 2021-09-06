package com.furianrt.entrieslist.mappers

import com.furianrt.domain.entities.Entry
import com.furianrt.entrieslist.entities.EntriesListItem
import com.furianrt.presentation.mapper.BaseMapper
import javax.inject.Inject

internal class EntriesListMapper @Inject constructor(

) : BaseMapper<List<@JvmSuppressWildcards Entry>, List<@JvmSuppressWildcards EntriesListItem>>() {

    override fun map(from: List<Entry>): List<EntriesListItem> {
        return from.map { EntriesListItem.Mood("Great") }
    }
}