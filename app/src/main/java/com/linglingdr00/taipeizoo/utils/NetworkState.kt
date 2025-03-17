package com.linglingdr00.taipeizoo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData

class NetworkState(private val context: Context): LiveData<Boolean>() {

    private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(false)
        }
    }

    override fun onActive() {
        super.onActive()
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        postValue(capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true)
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

}