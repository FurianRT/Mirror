package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.furianrt.storage.database.entities.DbMoodIcon
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodIconDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(icon: DbMoodIcon)

    @Query("SELECT * FROM ${DbMoodIcon.TABLE_NAME}")
    fun getAllIcons(): Flow<List<DbMoodIcon>>
}