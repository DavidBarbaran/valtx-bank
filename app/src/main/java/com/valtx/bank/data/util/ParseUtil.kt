package com.valtx.bank.data.util

import com.valtx.bank.data.entity.ApiErrorResponse
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody

fun parseApiError(
    errorBody: ResponseBody?,
): ApiErrorResponse? {
    return try {
        errorBody?.string()?.let {
            Json.decodeFromString<ApiErrorResponse>(it)
        }
    } catch (e: Exception) {
        null
    }
}