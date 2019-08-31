package com.navigation.mobile.navigation.commonui.navigation

import androidx.lifecycle.ViewModel

open class NavigationViewModel : ViewModel() {
    /**
     * Live Data that handles passing Navigation Events
     */
    val navigationLiveDataField = LiveNavigationField<NavigationEvent>()
    /**
     * Helper function for navigating to the [destination] fragment from the current one
     */
    fun navigateTo(destination: NavigationDestination) {
        navigationLiveDataField.latestValue = destination.buildEvent()
    }
}
