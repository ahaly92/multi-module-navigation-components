package com.navigation.mobile.android.navigation

import com.navigation.mobile.android.screen.main.LoginDestination
import com.navigation.mobile.navigation.commonui.navigation.NavigationViewModel

class MainViewModel : NavigationViewModel(){

    internal fun navigateToLogin() {
        navigateTo(LoginDestination())
    }
}
