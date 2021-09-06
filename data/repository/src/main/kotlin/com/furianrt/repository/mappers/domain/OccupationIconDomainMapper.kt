package com.furianrt.repository.mappers.domain

import com.furianrt.domain.entities.OccupationIcon
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbOccupationIcon
import javax.inject.Inject

internal class OccupationIconDomainMapper @Inject constructor() :
    BaseMapper<OccupationIcon, DbOccupationIcon>() {

    override suspend fun map(from: OccupationIcon)= DbOccupationIcon(from.name)
}