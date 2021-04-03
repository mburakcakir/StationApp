package com.mburakcakir.stationapp.ui.location

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.databinding.DialogGoogleMapSearchBinding

class GoogleMapSearchDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DialogGoogleMapSearchBinding

    private val args by navArgs<GoogleMapSearchDialogArgs>()

    override fun getTheme(): Int = R.style.BottomSheetDialog

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogGoogleMapSearchBinding.inflate(inflater, container, false)
        isCancelable = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        binding.rgOptions.setOnCheckedChangeListener { radioGroup, checkId ->
            val text = when (checkId) {
                binding.rbBus.id -> getString(R.string.bus)
                binding.rbSubway.id -> getString(R.string.subway)
                binding.rbMetrobus.id -> getString(R.string.metrobus)
                else -> getString(R.string.bus)
            }
            setGoogleMapSearch(text)
        }

        binding.btnSearchOption.setOnClickListener {
            binding.edtOtherOption.text?.let {
                setGoogleMapSearch(it.toString())
            }
        }
    }

    private fun setGoogleMapSearch(keyword: String) {
        val location = args.location
        val uriString = Uri.parse("geo:${location.latitude},${location.longitude}?q=$keyword")
        Intent(Intent.ACTION_VIEW, uriString).apply {
            setPackage("com.google.android.apps.maps")
            startActivity(this)
        }
    }
}