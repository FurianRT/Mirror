package com.furianrt.storage.database.dao

import androidx.room.Transaction

interface BaseDao {

    @Transaction
    suspend fun transaction(action: suspend () -> Unit) = action()
}