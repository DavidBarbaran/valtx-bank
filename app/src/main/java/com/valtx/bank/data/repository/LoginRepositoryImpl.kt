package com.valtx.bank.data.repository

import android.os.Build
import com.valtx.bank.BuildConfig
import com.valtx.bank.data.api.RestApi
import com.valtx.bank.data.providers.DeviceInfoProvider
import com.valtx.bank.data.entity.login.AppRequest
import com.valtx.bank.data.entity.login.DeviceRequest
import com.valtx.bank.data.entity.login.LoginRequest
import com.valtx.bank.data.entity.login.ProfileRequest
import com.valtx.bank.data.entity.login.UserRequest
import com.valtx.bank.data.util.ANDROID
import com.valtx.bank.data.util.DEFAULT_ERROR_MESSAGE
import com.valtx.bank.data.util.UNKNOWN
import com.valtx.bank.data.util.parseApiError
import com.valtx.bank.domain.repository.LoginRepository
import com.valtx.bank.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: RestApi,
    private val deviceInfoProvider: DeviceInfoProvider
) : LoginRepository {

    override suspend fun login(
        document: String,
        password: String
    ): Result<Unit> = withContext(Dispatchers.IO) {

        val request = LoginRequest(
            user = UserRequest(
                userCode = document,
                pass = password,
                profile = ProfileRequest(language = "es")
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
                response.body()?.let {
                    Result.Success(Unit)
                } ?: Result.Error(DEFAULT_ERROR_MESSAGE)

            } else {
                val apiError = parseApiError(response.errorBody())

                Result.Error(
                    apiError?.error?.userMessage?.es ?: DEFAULT_ERROR_MESSAGE
                )
            }

        } catch (e: Exception) {
            Result.Error(DEFAULT_ERROR_MESSAGE)
        }
    }
}