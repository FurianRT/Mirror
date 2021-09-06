package com.furianrt.settings

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.furianrt.presentation.utils.withArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class SettingsFragment : Fragment(R.layout.fragment_settings) {

    companion object {
        @JvmStatic
        fun newInstance(args: SettingsArgs) = SettingsFragment().withArgs(args)
    }

    private val viewModel by viewModels<SettingsViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("onAttach", "SettingsFragment")
    }
}