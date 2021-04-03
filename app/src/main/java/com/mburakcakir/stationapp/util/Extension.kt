package com.mburakcakir.stationapp.util

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
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

//fun <T>MutableList<T>.sortList(parameter : Int): MutableList<T> {
//    this.sortBy {
//        parameter
//    }
//    return this
//}

