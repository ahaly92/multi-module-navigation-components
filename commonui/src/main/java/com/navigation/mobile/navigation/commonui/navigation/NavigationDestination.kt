package com.navigation.mobile.navigation.commonui.navigation

/**
 * A class that accepts an [id] and the list of [navigationArguments] and is able to
 * return a [NavigationEvent]
 */
open class NavigationDestination(
    private val id: Int,
    private val navigationArguments: NavigationArguments? = null
) {
    /**
     * Generates a navigation event from the [id] and [navigationArguments] of the
     * [NavigationDestination] class.
     */
    fun buildEvent(): NavigationEvent = NavigationEvent(id, navigationArguments)
}
