package com.surkhojb.daggerhiltsample.data

import android.content.Context
import android.net.ConnectivityManager
import com.surkhojb.daggerhiltsample.data.settings.SettingsDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val settingsDataSource: SettingsDataSource) {

    @Inject
    lateinit var context: Context

    suspend fun getMaxResult() = settingsDataSource.getMaxResults()

    suspend fun getOrderBy(): String {
        if(settingsDataSource.getOrderBy().equals(""))
            return "ORDER_BY_UNDEFINED"
        else
            return settingsDataSource.getOrderBy().toString()
    }

    suspend fun getOfflineSupport() = settingsDataSource.getOfflineSupport()

    fun isNetworkAvaliable(): Boolean?{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo

        networkInfo?.let {
            return it.isAvailable && it.isConnected
        } ?: return false
    }
}