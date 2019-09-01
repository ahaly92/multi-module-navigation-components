package com.navigation.mobile.android.repository.login

import com.navigation.mobile.android.core.model.ServiceResult
import com.navigation.mobile.android.core.model.User

interface LoginRepository {
    fun login(username: String, password: String): ServiceResult<User>
}
