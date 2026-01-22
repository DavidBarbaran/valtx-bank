package com.valtx.bank.data.repository

import android.os.Build
import com.valtx.bank.BuildConfig
import com.valtx.bank.data.api.RestApi
import com.valtx.bank.data.providers.DeviceInfoProvider
import com.valtx.bank.data.entity.AppRequest
import com.valtx.bank.data.entity.DeviceRequest
import com.valtx.bank.data.entity.LoginRequest
import com.valtx.bank.domain.login.LoginResult
import com.valtx.bank.data.entity.ProfileRequest
import com.valtx.bank.data.entity.UserRequest
import com.valtx.bank.data.util.parseApiError
import com.valtx.bank.domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: RestApi,
    private val deviceInfoProvider: DeviceInfoProvider
) : LoginRepository {

    companion object {
        private const val UNKNOWN = "Unknown"
        private const val ANDROID = "android"
        private const val DEFAULT_ERROR_MESSAGE = "Ocurrió un error inesperado, vuelva a intentarlo"
    }

    override suspend fun login(
        document: String,
        password: String
    ): LoginResult = withContext(Dispatchers.IO) {

        val request = LoginRequest(
            user = UserRequest(
                userCode = document,
                pass = password,
                profile = ProfileRequest(
                    language = "es"
                )
            ),
            device = DeviceRequest(
                deviceId = deviceInfoProvider.generateDeviceId(),
                name = Build.MODEL ?: UNKNOWN,
                version = Build.VERSION.RELEASE ?: UNKNOWN,
                width = deviceInfoProvider.getScreenWidth(),
                height = deviceInfoProvider.getScreenHeight(),
                model = Build.MODEL ?: UNKNOWN,
                platform = ANDROID
            ),
            app = AppRequest(
                version = BuildConfig.VERSION_NAME
            )
        )

        return@withContext try {
            val response = api.login(request)

            if (response.isSuccessful) {

                val body = response.body()
                if (body != null) {
                    LoginResult.Success
                } else {
                    LoginResult.Error(DEFAULT_ERROR_MESSAGE)
                }

            } else {
                val apiError = parseApiError(response.errorBody())

                LoginResult.Error(
                    apiError?.error?.userMessage?.es
                        ?: DEFAULT_ERROR_MESSAGE
                )
            }

        } catch (e: Exception) {
            LoginResult.Error(DEFAULT_ERROR_MESSAGE)
        }
    }
}