package com.furianrt.entrieslist.api

import androidx.fragment.app.Fragment
import com.furianrt.entrieslist.EntriesListArgs
import com.furianrt.entrieslist.EntriesListFragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntriesListApi @Inject constructor() {

    private val listScrollRelay = MutableStateFlow(1)

    fun entriesListFragment(args: EntriesListArgs): Fragment = EntriesListFragment.newInstance(args)

    fun observeEntriesListScrollDy() = listScrollRelay.asStateFlow()

    internal fun postListScrollDy(dy: Int) {
        listScrollRelay.value = dy
    }
}