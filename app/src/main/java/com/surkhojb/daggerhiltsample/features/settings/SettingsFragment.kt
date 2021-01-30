package com.surkhojb.daggerhiltsample.features.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.surkhojb.daggerhiltsample.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

}