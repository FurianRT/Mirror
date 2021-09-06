package com.furianrt.quotes.api

import androidx.fragment.app.Fragment
import com.furianrt.quotes.QuotesArgs
import com.furianrt.quotes.QuotesFragment
import javax.inject.Inject

class QuotesApi @Inject constructor() {
    fun quotesFragment(args: QuotesArgs): Fragment = QuotesFragment.newInstance(args)
}