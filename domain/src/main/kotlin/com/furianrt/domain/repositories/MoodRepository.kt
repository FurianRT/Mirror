package com.furianrt.domain.repositories

import com.furianrt.domain.entities.Mood
import com.furianrt.domain.entities.MoodIcon
import kotlinx.coroutines.flow.Flow

interface MoodRepository {
    suspend fun insertMood(mood: Mood)
    fun getAllMoods(): Flow<List<Mood>>
    fun getAllIcons(): Flow<List<MoodIcon>>
}