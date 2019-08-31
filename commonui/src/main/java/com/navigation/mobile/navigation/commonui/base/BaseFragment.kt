package com.navigation.mobile.navigation.commonui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.navigation.mobile.navigation.commonui.navigation.LiveNavigationField
import com.navigation.mobile.navigation.commonui.navigation.NavigationEvent

abstract class BaseFragment : Fragment() {
    /**
     * The [layoutResourceId] of the resource file to be inflated in the fragment.
     */
    protected abstract val layoutResourceId: Int

    /**
     * Helper method that overrides onCreateView to automatically inflate the layout provided
     * within [layoutResourceId]
     */
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(layoutResourceId, container, false)

    /**
     * Fragment extension function for obtaining an [instance] of the view model through ViewModelProviders
     */
    @Suppress("UNCHECKED_CAST")
    inline fun <reified V : ViewModel> Fragment.obtainViewModel(crossinline instance: () -> V): V {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return instance() as T
            }
        }
        return ViewModelProviders.of(this, factory).get(V::class.java)
    }

    /**
     * Sets up the fragment to listen for navigation events from [navigationLiveDataField]
     */
    protected fun configureNavigationListener(navigationLiveDataField: LiveNavigationField<NavigationEvent>) {
        navigationLiveDataField.observe(viewLifecycleOwner, Observer { navigate(it) })
    }

    private fun navigate(event: NavigationEvent) {
        findNavController().navigate(event.navId, event.argumentsBundle())
    }
}
