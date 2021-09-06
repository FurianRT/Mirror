package com.furianrt.storage.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.furianrt.storage.database.entities.DbMood
import com.furianrt.storage.database.entities.DbMoodIcon
import com.furianrt.storage.database.entities.link.DbMoodIconLink

data class DbMoodWithIcon(
    @Embedded val mood: DbMood,

    @Relation(
        entity = DbMoodIcon::class,
        entityColumn = DbMoodIcon.FIELD_NAME,
        parentColumn = DbMood.FIELD_ID,
        associateBy = Junction(
            value = DbMoodIconLink::class,
            entityColumn = DbMoodIconLink.FIELD_ICON_NAME,
            parentColumn = DbMoodIconLink.FIELD_MOOD_ID
        )
    )
    val icon: DbMoodIcon
)