package com.example.animalsdatabase.ui.common.preferences

import android.os.Bundle
import android.os.PersistableBundle
import androidx.preference.PreferenceFragmentCompat
import com.example.animalsdatabase.R

class PreferenceSettings: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)

    }


}