package com.furianrt.statistics.api

import androidx.fragment.app.Fragment
import com.furianrt.statistics.StatisticsArgs
import com.furianrt.statistics.StatisticsFragment
import javax.inject.Inject

class StatisticsApi @Inject constructor() {
    fun statisticsFragment(args: StatisticsArgs): Fragment = StatisticsFragment.newInstance(args)
}