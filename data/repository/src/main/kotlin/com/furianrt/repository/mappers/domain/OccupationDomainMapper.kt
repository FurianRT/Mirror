package com.furianrt.repository.mappers.domain

import com.furianrt.domain.entities.Occupation
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbOccupation
import com.furianrt.storage.database.entities.relations.DbOccupationWithIcon
import javax.inject.Inject

internal class OccupationDomainMapper @Inject constructor(
    private val occupationIconDomainMapper: OccupationIconDomainMapper
) : BaseMapper<Occupation, DbOccupationWithIcon>() {

    override suspend fun map(from: Occupation) = DbOccupationWithIcon(
        DbOccupation(from.id, from.name),
        occupationIconDomainMapper.map(from.icon)
    )
}