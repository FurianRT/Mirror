package com.furianrt.repository.mappers.domain

import com.furianrt.domain.entities.Image
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbImage
import javax.inject.Inject

internal class ImageDomainMapper @Inject constructor() : BaseMapper<Image, DbImage>() {

    override suspend fun map(from: Image) = DbImage(
        from.id,
        from.entryId,
        from.url,
        from.addedAt,
        from.order
    )
}