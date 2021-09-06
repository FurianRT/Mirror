package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.furianrt.storage.database.entities.DbMood
import com.furianrt.storage.database.entities.relations.DbMoodWithIcon
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(mood: DbMood)

    @Query("SELECT * FROM ${DbMood.TABLE_NAME}")
    fun getAllMoods(): Flow<List<DbMoodWithIcon>>
}