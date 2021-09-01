package com.example.animalsdatabase.ui.common

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.animalsdatabase.ui.BaseFragment
import kotlinx.android.synthetic.main.recycler_view_layout.*

abstract class RecyclerViewFragment(@LayoutRes contentLayoutId: Int) : BaseFragment(contentLayoutId), Scrollable {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.itemAnimator = null
    }

    override fun scrollToTop() {
        recyclerView?.smoothScrollToPosition(0)
    }

    open fun scrollToTopQuick() {
        recyclerView.scrollToPosition(0)
    }

    fun registerDefaultSearchableObserver(adapter: RecyclerView.Adapter<*>) {
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                if (isResumed) {
                    scrollToTopQuick()
                }
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                scrollToTopQuick()
            }
        })
    }
}