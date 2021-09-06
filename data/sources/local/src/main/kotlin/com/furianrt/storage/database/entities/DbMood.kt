package com.furianrt.storage.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.util.Locale

@Entity(tableName = DbMood.TABLE_NAME)
data class DbMood(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = FIELD_ID)
    val id: String,

    @ColumnInfo(name = FIELD_NAME)
    val name: String,

    @ColumnInfo(name = FIELD_CATEGORY)
    val category: Category,

    @ColumnInfo(name = FIELD_CUSTOM)
    val custom: Boolean
) {

    enum class Category {
        GREAT,
        GOOD,
        OKAY,
        BAD,
        AWFUL
    }

    internal class Converter {

        @TypeConverter
        fun stringToCategory(string: String) = Category.valueOf(string.uppercase(Locale.ENGLISH))

        @TypeConverter
        fun categoryToString(category: Category) = category.name.lowercase(Locale.ENGLISH)
    }

    companion object {
        const val TABLE_NAME = "moods"
        const val FIELD_ID = "id"
        const val FIELD_NAME = "name"
        const val FIELD_CATEGORY = "category"
        const val FIELD_CUSTOM = "custom"
    }
}