package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.furianrt.storage.database.entities.DbImage

@Dao
interface ImageDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(image: DbImage)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(images: List<DbImage>)
}