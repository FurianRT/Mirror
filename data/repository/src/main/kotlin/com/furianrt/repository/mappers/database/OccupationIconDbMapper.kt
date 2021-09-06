package com.furianrt.repository.mappers.database

import com.furianrt.domain.entities.OccupationIcon
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbOccupationIcon
import javax.inject.Inject

internal class OccupationIconDbMapper @Inject constructor() :
    BaseMapper<DbOccupationIcon, OccupationIcon>() {

    override suspend fun map(from: DbOccupationIcon) = OccupationIcon(from.name)
}