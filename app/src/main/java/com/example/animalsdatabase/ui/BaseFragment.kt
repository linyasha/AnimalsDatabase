package com.example.animalsdatabase.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.animalsdatabase.R
import com.example.animalsdatabase.utils.hideKeyboard

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Toolbar>(R.id.toolbar)?.setNavigationOnClickListener { onNavigationClick() }
    }

    protected fun getBaseActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    open fun onNavigationClick() {
        val activity = requireActivity()
        activity.currentFocus?.hideKeyboard()
        activity.onBackPressed()
    }
}