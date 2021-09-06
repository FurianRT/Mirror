package com.furianrt.storage.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = DbImage.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = DbEntry::class,
            parentColumns = [DbEntry.FIELD_ID],
            childColumns = [DbImage.FIELD_ENTRY_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DbImage(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = FIELD_ID)
    val id: String,

    @ColumnInfo(name = FIELD_ENTRY_ID, index = true)
    val entryId: String,

    @ColumnInfo(name = FIELD_URL)
    val url: String,

    @ColumnInfo(name = FIELD_ADDED_AT)
    val addedAt: Long,

    @ColumnInfo(name = FIELD_ORDER)
    val order: Int
) {

    companion object {
        const val TABLE_NAME = "images"
        const val FIELD_ID = "id"
        const val FIELD_ENTRY_ID = "id_entry"
        const val FIELD_URL = "url"
        const val FIELD_ADDED_AT = "added_at"
        const val FIELD_ORDER = "order"
    }
}