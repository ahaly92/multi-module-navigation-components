package com.navigation.mobile.android.navigation.screen.main

import android.os.Bundle
import android.view.View
import com.navigation.mobile.android.navigation.R
import com.navigation.mobile.navigation.commonui.base.BaseFragment

class MainFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.main_fragment

    private val viewModel by lazy {
        obtainViewModel {
            MainViewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureViewModel()
        viewModel.navigateToLogin()
    }

    private fun configureViewModel() {
        configureNavigationListener(viewModel.navigationLiveDataField)
    }
}
