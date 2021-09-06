package com.furianrt.domain.usecase.entry

import com.furianrt.domain.base.BaseDispatcherProvider
import com.furianrt.domain.entities.EntriesSorting
import com.furianrt.domain.entities.Entry
import com.furianrt.domain.repositories.EntriesRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class GetAllEntriesUseCase @Inject constructor(
    private val entriesRepository: EntriesRepository,
    private val dispatchers: BaseDispatcherProvider
) {

    sealed class Result {
        data class Failure(val error: Throwable) : Result()
        data class Success(val entries: List<Entry>) : Result()
        object Loading : Result()
    }

    operator fun invoke(): Flow<Result> =
        entriesRepository.getAllEntries()
            .combine(entriesRepository.getEntriesSorting()) { entries, sorting ->
                when (sorting) {
                    EntriesSorting.ASC -> entries.sortedBy { it.timestamp }
                    EntriesSorting.DESC -> entries.sortedByDescending { it.timestamp }
                }
            }
            .map<List<Entry>, Result> { Result.Success(it) }
            .catch { emit(Result.Failure(it)) }
            .flowOn(dispatchers.background())
}