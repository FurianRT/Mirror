package com.furianrt.storage.database.entities.link

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.furianrt.storage.database.entities.DbEntry
import com.furianrt.storage.database.entities.DbOccupation

@Entity(
    tableName = DbEntryOccupationLink.TABLE_NAME,
    primaryKeys = [DbEntryOccupationLink.FIELD_ENTRY_ID, DbEntryOccupationLink.FIELD_OCCUPATION_ID],
    indices = [
        Index(value = [DbEntryOccupationLink.FIELD_ENTRY_ID], unique = false),
        Index(value = [DbEntryOccupationLink.FIELD_OCCUPATION_ID], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = DbEntry::class,
            parentColumns = [DbEntry.FIELD_ID],
            childColumns = [DbEntryOccupationLink.FIELD_ENTRY_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DbOccupation::class,
            parentColumns = [DbOccupation.FIELD_ID],
            childColumns = [DbEntryOccupationLink.FIELD_OCCUPATION_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DbEntryOccupationLink(
    @ColumnInfo(name = FIELD_ENTRY_ID)
    val entryId: String,

    @ColumnInfo(name = FIELD_OCCUPATION_ID)
    val occupationId: String
) {

    companion object {
        const val TABLE_NAME = "entry_occupation_link"
        const val FIELD_ENTRY_ID = "id_entry"
        const val FIELD_OCCUPATION_ID = "occupation_id"
    }
}