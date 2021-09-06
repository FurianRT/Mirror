package com.furianrt.storage.database.dao

import androidx.room.*
import com.furianrt.storage.database.entities.DbEntry
import com.furianrt.storage.database.entities.relations.DbFullEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao : BaseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: DbEntry)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entries: List<DbEntry>)

    @Update
    suspend fun update(entry: DbEntry)

    @Delete
    suspend fun delete(entry: DbEntry)

    @Delete
    suspend fun delete(entries: List<DbEntry>)

    @Transaction
    @Query("SELECT * FROM ${DbEntry.TABLE_NAME}")
    fun getAllEntries(): Flow<List<DbFullEntry>>
}