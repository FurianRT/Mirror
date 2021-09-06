package com.furianrt.domain.usecase.mood

import com.furianrt.domain.entities.MoodIcon
import com.furianrt.domain.repositories.MoodRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoodIconsUseCase @Inject constructor(
    private val moodRepository: MoodRepository
) {

    operator fun invoke(): Flow<List<MoodIcon>> = moodRepository.getAllIcons()
}