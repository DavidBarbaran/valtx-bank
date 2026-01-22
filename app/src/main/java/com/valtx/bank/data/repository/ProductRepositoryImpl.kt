package com.valtx.bank.data.repository

import com.valtx.bank.data.api.RestApi
import com.valtx.bank.data.mapper.toDomain
import com.valtx.bank.data.util.DEFAULT_ERROR_MESSAGE
import com.valtx.bank.data.util.parseApiError
import com.valtx.bank.domain.product.Product
import com.valtx.bank.domain.repository.ProductRepository
import com.valtx.bank.domain.result.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val api: RestApi,
) : ProductRepository {

    override suspend fun getProducts(): Result<List<Product>> = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = api.getProducts()

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    Result.Success(body.map { it.toDomain() })
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