package com.mburakcakir.stationapp.ui.station

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mburakcakir.stationapp.databinding.RvItemStationBinding
import com.mburakcakir.stationapp.network.model.Bus

class StationAdapter : ListAdapter<Bus, StationViewHolder>(StationCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        return StationViewHolder(
            RvItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class StationViewHolder(
    val binding: RvItemStationBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(busModel: Bus) {

    }
}

class StationCallback : DiffUtil.ItemCallback<Bus>() {
    override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean =
        oldItem == newItem
}