package com.furianrt.domain.usecase.mood

import com.furianrt.domain.base.BaseDispatcherProvider
import com.furianrt.domain.entities.Mood
import com.furianrt.domain.repositories.MoodRepository
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertMoodUseCase @Inject constructor(
    private val moodRepository: MoodRepository,
    private val dispatchers: BaseDispatcherProvider
) {

    suspend operator fun invoke(mood: Mood) =
        withContext(dispatchers.background() + NonCancellable) {
            moodRepository.insertMood(mood)
        }
}