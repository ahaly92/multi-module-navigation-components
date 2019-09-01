package com.navigation.mobile.android.screen.main

import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputLayout
import com.navigation.mobile.android.login.R
import com.navigation.mobile.android.repository.login.LoginRepositoryImpl
import com.navigation.mobile.navigation.commonui.base.BaseFragment
import com.navigation.mobile.navigation.commonui.utils.afterTextChanged
import kotlinx.android.synthetic.main.login_fragment_main.login_main_loginBtn
import kotlinx.android.synthetic.main.login_fragment_main.login_main_passwordEdit
import kotlinx.android.synthetic.main.login_fragment_main.login_main_passwordInput
import kotlinx.android.synthetic.main.login_fragment_main.login_main_usernameEdit
import kotlinx.android.synthetic.main.login_fragment_main.login_main_usernameInput

class LoginFragment : BaseFragment() {
    override val layoutResourceId: Int = R.layout.login_fragment_main

    private val viewModel by lazy {
        obtainViewModel {
            LoginViewModel(LoginRepositoryImpl())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        configureViewModel()
        configureUIListeners()
    }

    private fun configureUIListeners() {
        view?.apply {
            login_main_loginBtn.setOnClickListener { viewModel.onLoginClick() }
            login_main_usernameEdit.afterTextChanged {
                viewModel.clearUsernameError()
                viewModel.username.value = it
            }
            login_main_passwordEdit.afterTextChanged {
                viewModel.clearPasswordError()
                viewModel.password.value = it
            }
        }
    }

    private fun configureViewModel() {
        viewModel.usernameError.observe(
            this, Observer { showFieldError(login_main_usernameInput, it) })
        viewModel.passwordError.observe(
            this, Observer { showFieldError(login_main_passwordInput, it) })
        viewModel.dialogError.observe(this, Observer { showErrorDialog(it) })
        configureNavigationListener(viewModel.navigationLiveDataField)
    }

    private fun showFieldError(textInputLayout: TextInputLayout, @StringRes errorId: Int?) {
        textInputLayout.apply {
            if (errorId != null) {
                error = getString(errorId)
                isErrorEnabled = true
            } else {
                isErrorEnabled = false
            }
        }
    }

    private fun showErrorDialog(message: String?) {
        message?.let {
            AlertDialog.Builder(requireContext())
                .setMessage(message)
                .setPositiveButton(android.R.string.ok) { _, _ -> viewModel.clearDialogError() }
                .setOnCancelListener { viewModel.clearDialogError() }
                .create()
                .show()
        }
    }
}
