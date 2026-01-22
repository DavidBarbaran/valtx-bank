package com.valtx.bank.data.repository

import com.valtx.bank.data.api.RestApi
import com.valtx.bank.data.mapper.toDomain
import com.valtx.bank.data.util.DEFAULT_ERROR_MESSAGE
import com.valtx.bank.data.util.parseApiError
import com.valtx.bank.domain.detail.AccountDetail
import com.valtx.bank.domain.repository.AccountDetailRepository
import com.valtx.bank.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountDetailRepositoryImpl @Inject constructor(
    private val api: RestApi,
) : AccountDetailRepository {

    override suspend fun getAccountDetail(): Result<AccountDetail> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val response = api.getAccountDetail()

                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        Result.Success(body.toDomain())
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