package com.navigation.mobile.android.navigation

import android.os.Bundle
import android.view.View
import com.navigation.mobile.navigation.commonui.base.BaseFragment
import kotlinx.android.synthetic.main.main_fragment.main_navigateToLoginBtn

class MainFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.main_fragment

    private val viewModel by lazy {
        obtainViewModel {
            MainViewModel()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureViewModel()
        configureUIListeners()
    }

    private fun configureUIListeners() {
        main_navigateToLoginBtn.setOnClickListener { viewModel.navigateToLogin() }
    }

    private fun configureViewModel() {
        configureNavigationListener(viewModel.navigationLiveDataField)
    }
}
