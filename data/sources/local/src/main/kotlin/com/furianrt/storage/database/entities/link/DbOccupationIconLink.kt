package com.furianrt.storage.database.entities.link

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.furianrt.storage.database.entities.DbOccupation
import com.furianrt.storage.database.entities.DbOccupationIcon

@Entity(
    tableName = DbOccupationIconLink.TABLE_NAME,
    primaryKeys = [
        DbOccupationIconLink.FIELD_OCCUPATION_ID,
        DbOccupationIconLink.FIELD_ICON_NAME
    ],
    indices = [
        Index(value = [DbOccupationIconLink.FIELD_OCCUPATION_ID], unique = false),
        Index(value = [DbOccupationIconLink.FIELD_ICON_NAME], unique = false)
    ],
    foreignKeys = [
        ForeignKey(
            entity = DbOccupation::class,
            parentColumns = [DbOccupation.FIELD_ID],
            childColumns = [DbOccupationIconLink.FIELD_OCCUPATION_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = DbOccupationIcon::class,
            parentColumns = [DbOccupationIcon.FIELD_NAME],
            childColumns = [DbOccupationIconLink.FIELD_ICON_NAME],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class DbOccupationIconLink(
    @ColumnInfo(name = FIELD_OCCUPATION_ID)
    val occupationId: String,

    @ColumnInfo(name = FIELD_ICON_NAME)
    val iconName: String
) {

    companion object {
        const val TABLE_NAME = "occupation_icon_link"
        const val FIELD_OCCUPATION_ID = "occupation_id"
        const val FIELD_ICON_NAME = "icon_name"
    }
}