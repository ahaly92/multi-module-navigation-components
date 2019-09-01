package com.navigation.mobile.android.screen.main

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.navigation.mobile.android.core.model.ServiceResult
import com.navigation.mobile.android.login.R
import com.navigation.mobile.android.repository.login.LoginRepository
import com.navigation.mobile.navigation.commonui.navigation.NavigationViewModel
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginRepository: LoginRepository
) : NavigationViewModel() {
    val password = MutableLiveData<String>()
    val username = MutableLiveData<String>()

    private val _usernameError = MutableLiveData<@StringRes Int?>()
    val usernameError: LiveData<Int?> = _usernameError

    private val _passwordError = MutableLiveData<@StringRes Int?>()
    val passwordError: LiveData<Int?> = _passwordError

    private val _dialogError = MutableLiveData<String>()
    val dialogError: LiveData<String> = _dialogError

    /**
     * Disables UI and makes a call to the backend to authenticate using
     * username and password
     */
    fun onLoginClick() {
        if (validateUI()) {
            disableUI(true)
            login()
        }
    }

    /**
     * Clears error on username field
     */
    fun clearUsernameError() {
        _usernameError.value = null
    }

    /**
     * Clears error on password field
     */
    fun clearPasswordError() {
        _passwordError.value = null
    }

    /**
     * Clears error on error dialog
     */
    fun clearDialogError() {
        _dialogError.value = null
    }

    private fun validateUI(): Boolean {
        return when {
            username.value.isNullOrEmpty() -> {
                _usernameError.value =
                    R.string.login_empty_username_error
                false
            }
            password.value.isNullOrEmpty() -> {
                _passwordError.value = R.string.login_empty_password_error
                false
            }
            else -> true
        }
    }

    private fun disableUI(disable: Boolean) {
        //TODO: Disable UI
    }

    private fun login() {
        viewModelScope.launch {
            when (val result =
                loginRepository.login(
                    username.value.orEmpty(),
                    password.value.orEmpty()
                )) {
                is ServiceResult.Error -> {
                    _dialogError.value = result.exception.string
                }
                is ServiceResult.Success<*> -> {
                    //TODO: navigate to next screen
                }
            }
            disableUI(false)
        }
    }
}
