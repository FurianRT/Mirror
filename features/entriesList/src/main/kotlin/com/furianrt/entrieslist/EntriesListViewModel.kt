package com.furianrt.entrieslist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.furianrt.domain.entities.Entry
import com.furianrt.domain.entities.Mood
import com.furianrt.domain.entities.MoodIcon
import com.furianrt.domain.usecase.entry.GetAllEntriesUseCase
import com.furianrt.domain.usecase.entry.InsertEntryUseCase
import com.furianrt.entrieslist.api.EntriesListApi
import com.furianrt.entrieslist.entities.EntriesListItem
import com.furianrt.entrieslist.mappers.EntriesListMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
internal class EntriesListViewModel @Inject constructor(
    private val getAllEntriesUseCase: GetAllEntriesUseCase,
    private val insertEntryUseCase: InsertEntryUseCase,
    private val entriesListMapper: EntriesListMapper,
    private val entriesListApi: EntriesListApi,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _entries = MutableStateFlow<List<EntriesListItem>>(emptyList())
    val entries = _entries.asStateFlow()

    init {
        loadEntries()
    }

    fun onTextClick() {
        addTestEntry()
    }

    private fun loadEntries() {
        viewModelScope.launch {
            getAllEntriesUseCase()
                .collect { result ->
                    when (result) {
                        is GetAllEntriesUseCase.Result.Success -> {
                            _entries.value = entriesListMapper.map(result.entries)
                        }
                        is GetAllEntriesUseCase.Result.Failure -> {

                        }
                        is GetAllEntriesUseCase.Result.Loading -> {

                        }
                    }
                }
        }
    }

    private fun addTestEntry() {
        viewModelScope.launch {
            insertEntryUseCase(
                Entry(
                    UUID.randomUUID().toString(),
                    "flmefmllefef",
                    System.currentTimeMillis(),
                    System.currentTimeMillis(),
                    System.currentTimeMillis(),
                    Mood(
                        "fewfew",
                        "fewfwef",
                        Mood.Category.GREAT,
                        true,
                        MoodIcon("mood_great_1")
                    ),
                    emptySet(),
                    emptyList()
                )
            )
        }
    }

    fun onEntriesListScroll(dy: Int) {
        entriesListApi.postListScrollDy(dy)
    }
}