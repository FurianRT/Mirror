package com.furianrt.storage.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(@ApplicationContext context: Context) {

    companion object {
        const val SORTING_ASC = 0
        const val SORTING_DESC = 1

        private val ENTRIES_SORTING = intPreferencesKey(name = "entries_sorting")
    }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "mirror")

    private val dataStore = context.dataStore

    fun getEntriesSorting(): Flow<Int> = dataStore.data
        .map { preferences -> preferences[ENTRIES_SORTING] ?: SORTING_DESC }

    suspend fun setEntriesSorting(sorting: Int) {
        dataStore.edit { preferences ->
            preferences[ENTRIES_SORTING] = sorting
        }
    }
}