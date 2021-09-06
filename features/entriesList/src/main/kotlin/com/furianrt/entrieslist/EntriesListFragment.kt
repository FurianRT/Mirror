package com.furianrt.entrieslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.furianrt.entrieslist.databinding.FragmentEntriesListBinding
import com.furianrt.presentation.utils.withArgs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
internal class EntriesListFragment : Fragment(R.layout.fragment_entries_list),
    EntriesListAdapter.EntriesListListener {

    companion object {
        @JvmStatic
        fun newInstance(args: EntriesListArgs) = EntriesListFragment().withArgs(args)
    }

    private val viewModel by viewModels<EntriesListViewModel>()

    private var binding: FragmentEntriesListBinding? = null

    @Inject
    lateinit var listAdapter: EntriesListAdapter

    private val listScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            viewModel.onEntriesListScroll(dy)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEntriesListBinding.bind(view)
        this.binding = binding
        setupEntriesList()
        getEntries()
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    override fun onStart() {
        super.onStart()
        binding?.listMoods?.addOnScrollListener(listScrollListener)
    }

    override fun onStop() {
        super.onStop()
        binding?.listMoods?.removeOnScrollListener(listScrollListener)
    }

    private fun setupEntriesList() {
        binding?.listMoods?.adapter = listAdapter
        binding?.listMoods?.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    private fun getEntries() {
        viewModel.entries
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { listAdapter.submitList(it) }
            .launchIn(lifecycleScope)
    }
}