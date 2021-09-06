package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.furianrt.storage.database.entities.link.DbMoodIconLink

@Dao
interface MoodIconLinkDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(moodIconLink: DbMoodIconLink)
}