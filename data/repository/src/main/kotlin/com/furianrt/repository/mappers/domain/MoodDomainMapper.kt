package com.furianrt.repository.mappers.domain

import com.furianrt.domain.entities.Mood
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbMood
import com.furianrt.storage.database.entities.DbMoodIcon
import com.furianrt.storage.database.entities.relations.DbMoodWithIcon
import javax.inject.Inject

internal class MoodDomainMapper @Inject constructor(
    private val moodIconDomainMapper: MoodIconDomainMapper
) : BaseMapper<Mood, DbMoodWithIcon>() {

    override suspend fun map(from: Mood) = DbMoodWithIcon(
        DbMood(from.id, from.name, map(from.category), from.custom),
        moodIconDomainMapper.map(from.icon)
    )

    private fun map(category: Mood.Category) = when (category) {
        Mood.Category.GREAT -> DbMood.Category.GREAT
        Mood.Category.GOOD -> DbMood.Category.GREAT
        Mood.Category.OKAY -> DbMood.Category.OKAY
        Mood.Category.BAD -> DbMood.Category.BAD
        Mood.Category.AWFUL -> DbMood.Category.AWFUL
    }
}