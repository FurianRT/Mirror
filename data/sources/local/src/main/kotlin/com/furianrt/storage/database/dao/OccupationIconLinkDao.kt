package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.furianrt.storage.database.entities.link.DbOccupationIconLink

@Dao
interface OccupationIconLinkDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(occupationIconLink: DbOccupationIconLink)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(occupationIconLink: List<DbOccupationIconLink>)
}