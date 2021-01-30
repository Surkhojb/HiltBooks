package com.surkhojb.daggerhiltsample.data.settings

import android.content.SharedPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SettingsDataSource @Inject constructor(private val preferences: SharedPreferences) {

    suspend fun getMaxResults() = withContext(Dispatchers.IO){
        preferences.getString("num_results","0")?.toInt()
    }

    suspend fun getOrderBy() = withContext(Dispatchers.IO){
        preferences.getString("order_by","")
    }

    suspend fun getOfflineSupport() = withContext(Dispatchers.IO){
        preferences.getBoolean("offline",false)
    }
}