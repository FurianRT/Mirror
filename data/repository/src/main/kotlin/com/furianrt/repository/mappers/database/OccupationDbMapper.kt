package com.furianrt.repository.mappers.database

import com.furianrt.domain.entities.Occupation
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbOccupation
import com.furianrt.storage.database.entities.relations.DbOccupationWithIcon
import javax.inject.Inject

internal class OccupationDbMapper @Inject constructor(
    private val occupationIconDbMapper: OccupationIconDbMapper
) : BaseMapper<DbOccupationWithIcon, Occupation>() {

    override suspend fun map(from: DbOccupationWithIcon) = Occupation(
        from.occupation.id,
        from.occupation.name,
        occupationIconDbMapper.map(from.icon)
    )
}