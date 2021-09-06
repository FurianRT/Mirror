package com.furianrt.repository.repositories

import com.furianrt.domain.entities.Mood
import com.furianrt.domain.entities.MoodIcon
import com.furianrt.domain.repositories.MoodRepository
import com.furianrt.repository.mappers.database.MoodDbMapper
import com.furianrt.repository.mappers.database.MoodIconDbMapper
import com.furianrt.repository.mappers.domain.MoodDomainMapper
import com.furianrt.repository.mappers.domain.MoodIconDomainMapper
import com.furianrt.storage.database.dao.MoodDao
import com.furianrt.storage.database.dao.MoodIconDao
import com.furianrt.storage.database.dao.MoodIconLinkDao
import com.furianrt.storage.database.entities.link.DbMoodIconLink
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class MoodRepositoryImp @Inject constructor(
    private val moodDao: MoodDao,
    private val moodIconDao: MoodIconDao,
    private val moodIconLinkDao: MoodIconLinkDao,
    private val moodDbMapper: MoodDbMapper,
    private val moodDomainMapper: MoodDomainMapper,
    private val moodIconDbMapper: MoodIconDbMapper,
    private val moodIconDomainMapper: MoodIconDomainMapper
) : MoodRepository {

    override suspend fun insertMood(mood: Mood) {
        moodDao.transaction { insertMoodInternal(mood) }
    }

    private suspend fun insertMoodInternal(mood: Mood) {
        val dbMoodWithIcon = moodDomainMapper.map(mood)
        moodDao.insert(dbMoodWithIcon.mood)
        moodIconLinkDao.insert(DbMoodIconLink(dbMoodWithIcon.mood.id, dbMoodWithIcon.icon.name))
    }

    override fun getAllMoods(): Flow<List<Mood>> =
        moodDao.getAllMoods()
            .map { moods -> moods.map { moodDbMapper.map(it) } }

    override fun getAllIcons(): Flow<List<MoodIcon>> =
        moodIconDao.getAllIcons()
            .map { moods -> moods.map { moodIconDbMapper.map(it) } }
}