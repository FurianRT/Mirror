package com.furianrt.storage.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DbEntry.TABLE_NAME)
data class DbEntry(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = FIELD_ID)
    val id: String,

    @ColumnInfo(name = FIELD_NOTE)
    val note: String,

    @ColumnInfo(name = FIELD_TIMESTAMP)
    val timestamp: Long,

    @ColumnInfo(name = FIELD_UPDATED_AT)
    val updatedAt: Long,

    @ColumnInfo(name = FIELD_CREATED_AT)
    val createdAt: Long
) {

    companion object {
        const val TABLE_NAME = "entries"
        const val FIELD_ID = "id"
        const val FIELD_NOTE = "note"
        const val FIELD_TIMESTAMP = "timestamp"
        const val FIELD_UPDATED_AT = "updated_at"
        const val FIELD_CREATED_AT = "created_at"
    }
}