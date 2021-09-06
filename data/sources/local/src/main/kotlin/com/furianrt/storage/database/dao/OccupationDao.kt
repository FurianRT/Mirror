package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.furianrt.storage.database.entities.DbOccupation

@Dao
interface OccupationDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(occupation: DbOccupation)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(occupations: List<DbOccupation>)
}