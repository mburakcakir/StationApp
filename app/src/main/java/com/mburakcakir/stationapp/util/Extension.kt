package com.mburakcakir.stationapp.util

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mburakcakir.stationapp.R
import com.mburakcakir.stationapp.network.model.Bus

infix fun Fragment.navigate(navDirections: NavDirections) {
    NavHostFragment.findNavController(this).navigate(navDirections)
}

infix fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun MutableList<Bus>.sortBusList(): MutableList<Bus> {
    this.sortBy {
        it.remainingTime
    }
    return this
}

infix fun Context.showDialogInfo(message: String) {
    MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.warning_dialog))
        .setMessage(message)
        .setCancelable(true)
        .setPositiveButton(getString(R.string.text_dialog_positive_button)) { dialogInterface: DialogInterface, i: Int ->
            dialogInterface.dismiss()
        }
        .show()
}