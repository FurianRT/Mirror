package com.furianrt.quotes

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.furianrt.presentation.utils.withArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class QuotesFragment : Fragment(R.layout.fragment_quotes) {

    companion object {
        @JvmStatic
        fun newInstance(args: QuotesArgs) = QuotesFragment().withArgs(args)
    }

    private val viewModel by viewModels<QuotesViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("onAttach", "QuotesFragment")
    }
}