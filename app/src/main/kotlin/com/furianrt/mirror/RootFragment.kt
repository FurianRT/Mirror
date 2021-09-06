package com.furianrt.mirror

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.furianrt.entrieslist.api.EntriesListApi
import com.furianrt.mirror.databinding.FragmentRootBinding
import com.furianrt.mirror.navigation.ScreenNavigator
import com.furianrt.presentation.utils.animateScaleXY
import com.furianrt.presentation.utils.animateTranslationX
import com.furianrt.presentation.utils.getCenterX
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
internal class RootFragment : Fragment(R.layout.fragment_root) {

    companion object {
        private const val BUNDLE_SELECTED_TAB = "selected_tab"
        private const val SELECTED_TAB_SCALE = 1.05f
        private const val UNSELECTED_TAB_SCALE = 1f
    }

    @Inject
    lateinit var navigator: ScreenNavigator

    @Inject
    lateinit var entriesListApi: EntriesListApi

    private var binding: FragmentRootBinding? = null

    private enum class Tab { ENTRIES, STATISTICS, SETTINGS, QUOTES }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addBackPressedCallback()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRootBinding.bind(view)
        this.binding = binding

        if (savedInstanceState == null) {
            navigator.toEntriesList()
            selectBottomTab(Tab.ENTRIES)
        }

        savedInstanceState?.getString(BUNDLE_SELECTED_TAB)?.let { tab ->
            selectBottomTab(Tab.valueOf(tab))
        }

        binding.buttonEntries.setOnClickListener {
            navigator.toEntriesList()
            selectBottomTab(Tab.ENTRIES)
        }
        binding.buttonStats.setOnClickListener {
            navigator.toStatistics()
            selectBottomTab(Tab.STATISTICS)
        }
        binding.buttonSettings.setOnClickListener {
            navigator.toSettings()
            selectBottomTab(Tab.SETTINGS)
        }
        binding.buttonQuotes.setOnClickListener {
            navigator.toQuotes()
            selectBottomTab(Tab.QUOTES)
        }

        getEntriesListScrollDy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        getSelectedTabTag()?.let { outState.putString(BUNDLE_SELECTED_TAB, it.name) }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    private fun addBackPressedCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    if (navigator.onBackPressed()) {
                        selectBottomTab(Tab.ENTRIES)
                    } else {
                        isEnabled = false
                        activity?.onBackPressed()
                    }
                }
            })
    }

    private fun selectBottomTab(tab: Tab) {
        binding?.buttonEntries?.isSelected = tab == Tab.ENTRIES
        binding?.buttonEntries?.animateScaleXY(
            if (tab == Tab.ENTRIES) SELECTED_TAB_SCALE else UNSELECTED_TAB_SCALE
        )

        binding?.buttonSettings?.isSelected = tab == Tab.SETTINGS
        binding?.buttonSettings?.animateScaleXY(
            if (tab == Tab.SETTINGS) SELECTED_TAB_SCALE else UNSELECTED_TAB_SCALE
        )

        binding?.buttonStats?.isSelected = tab == Tab.STATISTICS
        binding?.buttonStats?.animateScaleXY(
            if (tab == Tab.STATISTICS) SELECTED_TAB_SCALE else UNSELECTED_TAB_SCALE
        )

        binding?.buttonQuotes?.isSelected = tab == Tab.QUOTES
        binding?.buttonQuotes?.animateScaleXY(
            if (tab == Tab.QUOTES) SELECTED_TAB_SCALE else UNSELECTED_TAB_SCALE
        )

        binding?.appBar?.doOnLayout { moveSelectorTo(tab) }
    }

    private fun moveSelectorTo(tab: Tab) {
        val selectorHalfWidth = (binding?.viewSelector?.measuredWidth ?: return) / 2f
        val tabCenterX = when (tab) {
            Tab.ENTRIES -> binding?.buttonEntries?.getCenterX()
            Tab.SETTINGS -> binding?.buttonSettings?.getCenterX()
            Tab.STATISTICS -> binding?.buttonStats?.getCenterX()
            Tab.QUOTES -> binding?.buttonQuotes?.getCenterX()
        } ?: return

        binding?.viewSelector?.animateTranslationX(tabCenterX - selectorHalfWidth)
    }

    private fun getSelectedTabTag(): Tab? = when {
        binding?.buttonEntries?.isSelected == true -> Tab.ENTRIES
        binding?.buttonSettings?.isSelected == true -> Tab.SETTINGS
        binding?.buttonStats?.isSelected == true -> Tab.STATISTICS
        binding?.buttonQuotes?.isSelected == true -> Tab.QUOTES
        else -> null
    }

    private fun getEntriesListScrollDy() {
        entriesListApi.observeEntriesListScrollDy()
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { if (it > 0) binding?.appBar?.performHide() else binding?.appBar?.performShow() }
            .launchIn(lifecycleScope)
    }
}