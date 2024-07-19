package com.dinesh.personmanagement.common.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface NetworkInfo {
    fun isNetworkAvailable(): Boolean
}

class NetworkInfoImpl(private val context: Context) : NetworkInfo {
    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
