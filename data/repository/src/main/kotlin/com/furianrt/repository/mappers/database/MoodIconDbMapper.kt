package com.furianrt.repository.mappers.database

import com.furianrt.domain.entities.MoodIcon
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbMoodIcon
import javax.inject.Inject

internal class MoodIconDbMapper @Inject constructor() : BaseMapper<DbMoodIcon, MoodIcon>() {

    override suspend fun map(from: DbMoodIcon)= MoodIcon(from.name)
}