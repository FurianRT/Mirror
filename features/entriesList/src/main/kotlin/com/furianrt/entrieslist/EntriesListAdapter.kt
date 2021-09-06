package com.furianrt.entrieslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.furianrt.entrieslist.databinding.ListItemMoodBinding
import com.furianrt.entrieslist.entities.EntriesListItem
import javax.inject.Inject

internal class EntriesListAdapter @Inject constructor(
    private val listener: EntriesListListener
) : ListAdapter<EntriesListItem, EntriesListAdapter.BaseViewHolder>(
    AsyncDifferConfig.Builder(EntriesDiffCallback()).build()
) {

    override fun getItemViewType(position: Int): Int = getItem(position).getTypeId()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        when (viewType) {
            EntriesListItem.Mood.TYPE_ID -> MoodViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_mood, parent, false)
            )
            else -> throw IllegalArgumentException("Unsupported view type")
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        protected val binding = ListItemMoodBinding.bind(view)

        abstract fun bind(item: EntriesListItem)
    }

    private class MoodViewHolder(view: View) : BaseViewHolder(view) {
        override fun bind(item: EntriesListItem) {
            val mood = item as EntriesListItem.Mood
        }
    }

    private class EntriesDiffCallback : DiffUtil.ItemCallback<EntriesListItem>() {
        override fun areItemsTheSame(oldItem: EntriesListItem, newItem: EntriesListItem) = true
        override fun areContentsTheSame(oldItem: EntriesListItem, newItem: EntriesListItem) = true
    }

    interface EntriesListListener {

    }
}