package com.furianrt.domain.usecase.entry

import com.furianrt.domain.base.BaseDispatcherProvider
import com.furianrt.domain.entities.EntriesSorting
import com.furianrt.domain.repositories.EntriesRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChangeEntrySortingUseCase @Inject constructor(
    private val entriesRepository: EntriesRepository,
    private val dispatchers: BaseDispatcherProvider
) {

    suspend operator fun invoke(sorting: EntriesSorting) = withContext(dispatchers.background()) {
        entriesRepository.setEntriesSorting(sorting)
    }
}