package com.furianrt.storage.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.furianrt.storage.database.entities.*
import com.furianrt.storage.database.entities.link.DbEntryMoodLink
import com.furianrt.storage.database.entities.link.DbEntryOccupationLink

data class DbFullEntry(
    @Embedded val entry: DbEntry,

    @Relation(
        entity = DbMood::class,
        entityColumn = DbMood.FIELD_ID,
        parentColumn = DbEntry.FIELD_ID,
        associateBy = Junction(
            value = DbEntryMoodLink::class,
            parentColumn = DbEntryMoodLink.FIELD_ENTRY_ID,
            entityColumn = DbEntryMoodLink.FIELD_MOOD_ID
        )
    )
    val mood: DbMoodWithIcon,

    @Relation(
        entity = DbOccupation::class,
        entityColumn = DbOccupation.FIELD_ID,
        parentColumn = DbEntry.FIELD_ID,
        associateBy = Junction(
            value = DbEntryOccupationLink::class,
            entityColumn = DbEntryOccupationLink.FIELD_OCCUPATION_ID,
            parentColumn = DbEntryOccupationLink.FIELD_ENTRY_ID
        )
    )
    val occupations: List<DbOccupationWithIcon>,

    @Relation(
        entity = DbImage::class,
        entityColumn = DbImage.FIELD_ENTRY_ID,
        parentColumn = DbEntry.FIELD_ID
    )
    val images: List<DbImage>,
)