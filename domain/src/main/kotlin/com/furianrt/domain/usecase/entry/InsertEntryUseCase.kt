package com.furianrt.domain.usecase.entry

import com.furianrt.domain.base.BaseDispatcherProvider
import com.furianrt.domain.entities.Entry
import com.furianrt.domain.repositories.EntriesRepository
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertEntryUseCase @Inject constructor(
    private val entriesRepository: EntriesRepository,
    private val dispatchers: BaseDispatcherProvider
) {

    suspend operator fun invoke(entry: Entry) =
        withContext(dispatchers.background() + NonCancellable) {
            entriesRepository.insertEntry(entry)
        }
}