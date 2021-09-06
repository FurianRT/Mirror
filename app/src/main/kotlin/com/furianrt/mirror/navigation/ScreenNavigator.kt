package com.furianrt.mirror.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.furianrt.entrieslist.EntriesListArgs
import com.furianrt.entrieslist.api.EntriesListApi
import com.furianrt.mirror.R
import com.furianrt.mirror.RootFragment
import com.furianrt.mirror.di.MainFragmentManager
import com.furianrt.presentation.utils.inTransaction
import com.furianrt.quotes.QuotesArgs
import com.furianrt.quotes.api.QuotesApi
import com.furianrt.settings.SettingsArgs
import com.furianrt.settings.api.SettingsApi
import com.furianrt.statistics.StatisticsArgs
import com.furianrt.statistics.api.StatisticsApi
import javax.inject.Inject

internal class ScreenNavigator @Inject constructor(
    @MainFragmentManager private val fragmentManager: FragmentManager,
    private val entriesListApi: EntriesListApi,
    private val settingsApi: SettingsApi,
    private val statisticsApi: StatisticsApi,
    private val quotesApi: QuotesApi
) {

    private enum class Screen {
        ENTRIES,
        STATISTICS,
        SETTINGS,
        QUOTES
    }

    fun toEntriesList() {
        selectFragment(Screen.ENTRIES) { entriesListApi.entriesListFragment(EntriesListArgs()) }
    }

    fun toStatistics() {
        selectFragment(Screen.STATISTICS) { statisticsApi.statisticsFragment(StatisticsArgs()) }
    }

    fun toSettings() {
        selectFragment(Screen.SETTINGS) { settingsApi.settingsFragment(SettingsArgs()) }
    }

    fun toQuotes() {
        selectFragment(Screen.QUOTES) { quotesApi.quotesFragment(QuotesArgs()) }
    }

    private fun selectFragment(tag: Screen, createFragment: () -> Fragment) {
        with(getRootFragmentFm() ?: return) {
            val selectedFragment = fragments.find { it.isVisible }

            if (selectedFragment?.tag == tag.name) {
                return
            }

            inTransaction {
                fragments.forEach { fragment ->
                    if (fragment.isVisible) {
                        hide(fragment)
                    }
                }

                val existingFragment = findFragmentByTag(tag.name)

                if (existingFragment == null) {
                    add(R.id.root_fragment_container, createFragment(), tag.name)
                } else {
                    show(existingFragment)
                }
            }
        }
    }

    fun onBackPressed(): Boolean {
        val rootFragmentManager = getRootFragmentFm()

        return when {
            rootFragmentManager == null -> {
                false
            }
            rootFragmentManager.fragments.find { it.isVisible }?.tag != Screen.ENTRIES.name -> {
                selectFragment(Screen.ENTRIES) { entriesListApi.entriesListFragment(EntriesListArgs()) }
                true
            }
            else -> false
        }
    }

    private fun getRootFragmentFm() = fragmentManager.fragments.find { it is RootFragment }
        ?.childFragmentManager
}