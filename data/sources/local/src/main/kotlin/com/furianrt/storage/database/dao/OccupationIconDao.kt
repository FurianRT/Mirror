package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.furianrt.storage.database.entities.DbOccupationIcon
import kotlinx.coroutines.flow.Flow

@Dao
interface OccupationIconDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(icon: DbOccupationIcon)

    @Query("SELECT * FROM ${DbOccupationIcon.TABLE_NAME}")
    fun getAllMoods(): Flow<List<DbOccupationIcon>>
}