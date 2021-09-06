package com.furianrt.repository.mappers.domain

import com.furianrt.domain.entities.MoodIcon
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbMoodIcon
import javax.inject.Inject

internal class MoodIconDomainMapper @Inject constructor() : BaseMapper<MoodIcon, DbMoodIcon>() {

    override suspend fun map(from: MoodIcon) = DbMoodIcon(from.name)
}