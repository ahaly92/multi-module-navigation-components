package com.navigation.mobile.navigation.commonui.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.OnLifecycleEvent

/**
 * A class that wraps a Mutable Live Data in a way that it will only fire once instead of firing on every device rotation.
 */
class LiveNavigationField<T : Any> : LifecycleObserver {
    private var liveData = MutableLiveData<T>()

    private var lifecycleOwner: LifecycleOwner? = null
    /**
     * The current value of the LiveNavigationField
     */
    var latestValue: T?
        get() = liveData.value
        set(value) {
            liveData.value = value
        }

    /**
     * A wrapper around observe that ensures only one [owner] can pass an [observer] that listes to the live data at a time
     */
    fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        check(lifecycleOwner == null) { "Function 'observe' can only be called once during each lifecycle" }

        lifecycleOwner = owner.also {
            it.lifecycle.addObserver(this)
        }

        liveData.observe(owner, observer)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        lifecycleOwner?.let {
            lifecycleOwner = null
            liveData = MutableLiveData()
            it.lifecycle.removeObserver(this)
        }
    }
}
