package com.furianrt.statistics

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.furianrt.presentation.utils.withArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class StatisticsFragment : Fragment(R.layout.fragment_statistics) {

    companion object {
        @JvmStatic
        fun newInstance(args: StatisticsArgs) = StatisticsFragment().withArgs(args)
    }

    private val viewModel by viewModels<StatisticsViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("onAttach", "StatisticsFragment")
    }
}