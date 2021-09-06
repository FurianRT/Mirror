package com.furianrt.settings.api

import androidx.fragment.app.Fragment
import com.furianrt.settings.SettingsArgs
import com.furianrt.settings.SettingsFragment
import javax.inject.Inject

class SettingsApi @Inject constructor() {
    fun settingsFragment(args: SettingsArgs): Fragment = SettingsFragment.newInstance(args)
}