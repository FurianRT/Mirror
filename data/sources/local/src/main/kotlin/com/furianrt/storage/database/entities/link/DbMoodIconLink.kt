package com.furianrt.storage.database.entities.link

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.furianrt.storage.database.entities.DbMood
import com.furianrt.storage.database.entities.DbMoodIcon

@Entity(
    tableName = DbMoodIconLink.TABLE_NAME,
    primaryKeys = [DbMoodIconLink.FIELD_MOOD_ID, DbMoodIconLink.FIELD_ICON_NAME],
    indices = [
        Index(value = [DbMoodIconLink.FIELD_MOOD_ID], unique = false),
        Index(value = [DbMoodIconLink.FIELD_ICON_NAME], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = DbMood::class,
            parentColumns = [DbMood.FIELD_ID],
            childColumns = [DbMoodIconLink.FIELD_MOOD_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DbMoodIcon::class,
            parentColumns = [DbMoodIcon.FIELD_NAME],
            childColumns = [DbMoodIconLink.FIELD_ICON_NAME],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DbMoodIconLink(
    @ColumnInfo(name = FIELD_MOOD_ID)
    val moodId: String,

    @ColumnInfo(name = FIELD_ICON_NAME)
    val iconName: String
) {

    companion object {
        const val TABLE_NAME = "mood_icon_link"
        const val FIELD_MOOD_ID = "mood_id"
        const val FIELD_ICON_NAME = "icon_name"
    }
}