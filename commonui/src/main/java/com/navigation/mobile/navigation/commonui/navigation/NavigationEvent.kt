package com.navigation.mobile.navigation.commonui.navigation

import android.os.Bundle
import androidx.annotation.IdRes

/**
 * Class to wrap the navigation ID and appropriate arguments that need to be in the bundle while switching between fragments.
 *
 * @param navId = the ID to navigate too
 * @param navigationArguments: The Map of keys to values of various parameters that need to be passed into the next screen.
 */
data class NavigationEvent(@IdRes val navId: Int, val navigationArguments: NavigationArguments? = null) {
    /**
     * Function to convert from NavigationArguments back to an actual Bundle object.
     */
    fun argumentsBundle(): Bundle? = navigationArguments?.asBundle()
}
