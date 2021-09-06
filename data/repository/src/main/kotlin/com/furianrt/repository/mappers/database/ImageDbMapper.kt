package com.furianrt.repository.mappers.database

import com.furianrt.domain.entities.Image
import com.furianrt.repository.base.BaseMapper
import com.furianrt.storage.database.entities.DbImage
import javax.inject.Inject

internal class ImageDbMapper @Inject constructor() : BaseMapper<DbImage, Image>() {

    override suspend fun map(from: DbImage) = Image(
        from.id,
        from.entryId,
        from.url,
        from.addedAt,
        from.order
    )
}