package com.furianrt.storage.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.furianrt.storage.database.entities.link.DbEntryOccupationLink

@Dao
interface EntryOccupationLinkDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entryOccupationLink: DbEntryOccupationLink)
}