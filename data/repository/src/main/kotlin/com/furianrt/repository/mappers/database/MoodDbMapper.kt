package com.furianrt.repository.mappers.database

import com.furianrt.domain.entities.Mood
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbMood
import com.furianrt.storage.database.entities.DbMoodIcon
import com.furianrt.storage.database.entities.relations.DbMoodWithIcon
import javax.inject.Inject

internal class MoodDbMapper @Inject constructor(
    private val moodIconDbMapper: MoodIconDbMapper
) : BaseMapper<DbMoodWithIcon, Mood>() {

    override suspend fun map(from: DbMoodWithIcon) = Mood(
        from.mood.id,
        from.mood.name,
        map(from.mood.category),
        from.mood.custom,
        moodIconDbMapper.map(from.icon)
    )

    private fun map(category: DbMood.Category) = when (category) {
        DbMood.Category.GREAT -> Mood.Category.GREAT
        DbMood.Category.GOOD -> Mood.Category.GREAT
        DbMood.Category.OKAY -> Mood.Category.OKAY
        DbMood.Category.BAD -> Mood.Category.BAD
        DbMood.Category.AWFUL -> Mood.Category.AWFUL
    }
}