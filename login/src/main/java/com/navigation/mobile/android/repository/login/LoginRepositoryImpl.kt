package com.navigation.mobile.android.repository.login

import com.navigation.mobile.android.core.model.ServiceException
import com.navigation.mobile.android.core.model.ServiceResult
import com.navigation.mobile.android.core.model.User

class LoginRepositoryImpl : LoginRepository {
    override fun login(username: String, password: String): ServiceResult<User> {
        return ServiceResult.Error(ServiceException(FALLBACK_ERROR))
    }

    companion object {
        private const val FALLBACK_ERROR = "sorry, login has not been implemented yet."
    }
}
