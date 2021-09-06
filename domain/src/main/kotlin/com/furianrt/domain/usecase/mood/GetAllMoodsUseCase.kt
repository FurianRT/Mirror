package com.furianrt.domain.usecase.mood

import com.furianrt.domain.base.BaseDispatcherProvider
import com.furianrt.domain.entities.Mood
import com.furianrt.domain.repositories.MoodRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAllMoodsUseCase @Inject constructor(
    private val moodRepository: MoodRepository,
    private val dispatchers: BaseDispatcherProvider
) {

    sealed class Result {
        data class Failure(val error: Throwable) : Result()
        data class Success(val moods: List<Mood>) : Result()
        object Loading : Result()
    }

    operator fun invoke(): Flow<Result> =
        moodRepository.getAllMoods()
            .map<List<Mood>, Result> { Result.Success(it) }
            .catch { emit(Result.Failure(it)) }
            .flowOn(dispatchers.background())
}