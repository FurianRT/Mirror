package com.furianrt.storage.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DbOccupation.TABLE_NAME,)
data class DbOccupation(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = FIELD_ID)
    val id: String,

    @ColumnInfo(name = FIELD_NAME)
    val name: String
) {

    companion object {
        const val TABLE_NAME = "occupations"
        const val FIELD_ID = "id"
        const val FIELD_NAME = "name"
    }
}