package com.valtx.bank.data.providers

import android.content.Context
import android.provider.Settings
import android.util.DisplayMetrics
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.UUID
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeviceInfoProvider @Inject constructor(
    @param:ApplicationContext private val context: Context,
) {
    private val metrics: DisplayMetrics
        get() = context.resources.displayMetrics

    fun generateDeviceId(): String {
        return try {
            Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            ) ?: UUID.randomUUID().toString()
        } catch (e: Exception) {
            UUID.randomUUID().toString()
        }
    }

    fun getScreenWidth(): String =
        metrics.widthPixels.toString()

    fun getScreenHeight(): String =
        metrics.heightPixels.toString()
}