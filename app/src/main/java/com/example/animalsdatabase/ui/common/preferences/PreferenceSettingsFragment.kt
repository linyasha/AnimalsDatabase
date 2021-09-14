package com.example.animalsdatabase.ui.common.preferences

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.lifecycle.Lifecycle
import androidx.preference.ListPreference
import androidx.preference.PreferenceFragmentCompat
import com.example.animalsdatabase.AnimalsDatabaseApp
import com.example.animalsdatabase.R
import com.example.animalsdatabase.ui.MainActivity
import com.example.animalsdatabase.ui.common.ListFragmentViewModel
import com.example.animalsdatabase.ui.common.ListFragmentViewModelFactory

class PreferenceSettingsFragment: PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}