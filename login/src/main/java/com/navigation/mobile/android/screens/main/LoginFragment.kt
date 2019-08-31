package com.navigation.mobile.android.screens.main

import android.os.Bundle
import android.view.View
import com.navigation.mobile.android.login.R
import com.navigation.mobile.navigation.commonui.base.BaseFragment

class LoginFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.login_fragment_main

    private val viewModel by lazy {
        obtainViewModel {
            LoginViewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureViewModel()
        configureUIListeners()
    }

    private fun configureUIListeners() {
    }

    private fun configureViewModel() {

    }
}
