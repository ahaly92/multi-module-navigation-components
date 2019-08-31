package com.navigation.mobile.navigation.commonui.navigation

import android.os.Bundle
import android.os.Parcelable

/**
 * A class that wraps Bundle in a way that it is unit testable. All functions map one to one to the Bundle interface.
 *
 * Always create these with [NavigationArguments.create] so that the implementation can be swapped
 * out for testing.
 */
internal class NavigationArgsImpl(private val bundle: Bundle = Bundle()) : NavigationArguments {
    override fun getString(key: String): String? = bundle.getString(key)
    override fun getInt(key: String): Int = bundle.getInt(key)
    override fun getParcelable(key: String): Parcelable? = bundle.getParcelable(key)

    override fun putInt(key: String, value: Int): NavigationArguments {
        bundle.putInt(key, value)
        return this
    }

    override fun putString(key: String, value: String): NavigationArguments {
        bundle.putString(key, value)
        return this
    }

    override fun putParcelable(key: String, value: Parcelable): NavigationArguments {
        bundle.putParcelable(key, value)
        return this
    }
}
