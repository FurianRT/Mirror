package com.furianrt.storage.database.entities.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.furianrt.storage.database.entities.DbOccupation
import com.furianrt.storage.database.entities.DbOccupationIcon
import com.furianrt.storage.database.entities.link.DbOccupationIconLink

class DbOccupationWithIcon(
    @Embedded val occupation: DbOccupation,

    @Relation(
        entity = DbOccupationIcon::class,
        entityColumn = DbOccupationIcon.FIELD_NAME,
        parentColumn = DbOccupation.FIELD_ID,
        associateBy = Junction(
            value = DbOccupationIconLink::class,
            entityColumn = DbOccupationIconLink.FIELD_ICON_NAME,
            parentColumn = DbOccupationIconLink.FIELD_OCCUPATION_ID
        )
    )
    val icon: DbOccupationIcon
)