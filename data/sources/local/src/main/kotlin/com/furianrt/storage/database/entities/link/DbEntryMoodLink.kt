package com.furianrt.storage.database.entities.link

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.furianrt.storage.database.entities.DbEntry
import com.furianrt.storage.database.entities.DbMood

@Entity(
    tableName = DbEntryMoodLink.TABLE_NAME,
    primaryKeys = [DbEntryMoodLink.FIELD_ENTRY_ID, DbEntryMoodLink.FIELD_MOOD_ID],
    indices = [
        Index(value = [DbEntryMoodLink.FIELD_ENTRY_ID], unique = false),
        Index(value = [DbEntryMoodLink.FIELD_MOOD_ID], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = DbEntry::class,
            parentColumns = [DbEntry.FIELD_ID],
            childColumns = [DbEntryMoodLink.FIELD_ENTRY_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DbMood::class,
            parentColumns = [DbMood.FIELD_ID],
            childColumns = [DbEntryMoodLink.FIELD_MOOD_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DbEntryMoodLink(
    @ColumnInfo(name = FIELD_ENTRY_ID)
    val entryId: String,

    @ColumnInfo(name = FIELD_MOOD_ID)
    val moodId: String
) {

    companion object {
        const val TABLE_NAME = "entry_mood_link"
        const val FIELD_ENTRY_ID = "id_entry"
        const val FIELD_MOOD_ID = "id_mood"
    }
}