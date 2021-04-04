package com.mburakcakir.stationapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkController(private val context: Context) {

    fun isInternetAvailable(): Boolean {

        var isInternetAvailable = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        // API 23 and higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cm?.run {
                cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                    isInternetAvailable = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                        else -> false
                    }
                }
            }
        }
        // API 22 and lower
        // info: App MIN SDK is 21.
        else {
            cm?.run {
                cm.activeNetworkInfo?.run {
                    if (type == ConnectivityManager.TYPE_WIFI) {
                        isInternetAvailable = true
                    } else if (type == ConnectivityManager.TYPE_MOBILE) {
                        isInternetAvailable = true
                    }
                }
            }
        }
        return isInternetAvailable
    }
}

