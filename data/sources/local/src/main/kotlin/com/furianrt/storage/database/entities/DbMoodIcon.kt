package com.furianrt.storage.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DbMoodIcon.TABLE_NAME)
data class DbMoodIcon(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = FIELD_NAME)
    val name: String
) {

    companion object {
        const val TABLE_NAME = "mood_icons"
        const val FIELD_NAME = "name"
    }
}