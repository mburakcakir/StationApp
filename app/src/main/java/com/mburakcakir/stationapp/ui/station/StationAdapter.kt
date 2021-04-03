package com.mburakcakir.stationapp.ui.station

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mburakcakir.stationapp.databinding.RvItemStationBinding
import com.mburakcakir.stationapp.network.model.Bus
import com.mburakcakir.stationapp.network.model.Location

class StationAdapter : ListAdapter<Bus, StationViewHolder>(StationCallback()) {

    private lateinit var stationOnClick: (Location) -> Unit

    fun setStationOnClickListener(stationOnClick: (Location) -> Unit) {
        this.stationOnClick = stationOnClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        return StationViewHolder(
            RvItemStationBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            stationOnClick
        )
    }

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) =
        holder.bind(getItem(position))
}

class StationViewHolder(
    private val binding: RvItemStationBinding,
    private val stationOnClick: (Location) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(bus: Bus) {
        binding.bus = bus

        itemView.setOnClickListener {
            stationOnClick(bus.location)
        }
    }
}

class StationCallback : DiffUtil.ItemCallback<Bus>() {
    override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean =
        oldItem == newItem
}