package com.mburakcakir.stationapp.ui.station

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.databinding.RvItemStationBinding
import com.mburakcakir.stationapp.network.model.Bus

class StationAdapter : ListAdapter<Bus, StationViewHolder>(StationCallback()) {

    private lateinit var stationOnClick: (Bus) -> Unit

    fun setStationOnClickListener(stationOnClick: (Bus) -> Unit) {
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
    private val stationOnClick: (Bus) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(bus: Bus) {
        binding.bus = bus
        binding.position = adapterPosition

        itemView.setOnClickListener {
            stationOnClick(bus)
        }
    }

    private fun setBackgroundResource() {
        if (adapterPosition % 2 == 1)
            itemView.setBackgroundResource(R.color.grayLighter)
    }

//    private fun setRemainingTimeResource() {
//            when(adapterPosition) {
//                0 -> binding.txtBusRemainingTime.setTextColor(R.color.red)
//                1 -> binding.txtBusRemainingTime.setTextColor()
//            }
//        )
//    }


}

class StationCallback : DiffUtil.ItemCallback<Bus>() {
    override fun areItemsTheSame(oldItem: Bus, newItem: Bus): Boolean =
        oldItem == newItem


    override fun areContentsTheSame(oldItem: Bus, newItem: Bus): Boolean =
        oldItem == newItem
}