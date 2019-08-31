package com.navigation.mobile.navigation.commonui.navigation

import android.os.Bundle
import android.os.Parcelable

/**
 * A class that wraps Bundle in a way that it is unit testable. All functions map one to one to
 * the Bundle interface.
 */

interface NavigationArguments {
    fun asBundle(): Bundle? = null

    fun getParcelable(key: String): Parcelable?
    fun getInt(key: String): Int
    fun getString(key: String): String?

    fun putParcelable(key: String, value: Parcelable): NavigationArguments
    fun putString(key: String, value: String): NavigationArguments
    fun putInt(key: String, value: Int): NavigationArguments

    companion object {
        fun Bundle.asNavigationArguments(): NavigationArguments = NavigationArgsImpl(this)

        /**
         * Holds a function that 1) creates a [NavigationArguments] and 2) uses [apply] to execute the supplied block.
         *
         * This allows us to write code like:
         * ```
         * class MyDestination(argVal: Int) :
         *     NavigationDestination(
         *         R.id.destination,
         *         NavigationArguments.create {
         *             putInt(ARG_KEY, argVal)
         *         }
         *     ) { }
         * ```
         *
         * Using this method of creation ensures that we'll be able to inject a test implementation
         * during unit testing by changing this out:
         * ```
         * @Test
         * fun setup() {
         *     NavigationArguments.create = { block -> NavigationArgsTestImpl().apply(block) }
         * }
         * ```
         */
        var create: (NavigationArguments.() -> Unit) -> NavigationArguments =
            { block -> NavigationArgsImpl().apply(block) }
    }
}
