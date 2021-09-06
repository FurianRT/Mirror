package com.furianrt.entrieslist.entities

internal sealed class EntriesListItem {

    fun clone(): EntriesListItem = when (this) {
        is Mood -> copy()
    }

    fun getTypeId() = when (this) {
        is Mood -> Mood.TYPE_ID
    }

    data class Mood(val name: String = "") : EntriesListItem() {
        companion object {
            const val TYPE_ID = 0
        }
    }
}