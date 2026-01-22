package com.valtx.bank.domain.repository

import com.valtx.bank.domain.product.Product
import com.valtx.bank.domain.result.Result

interface ProductRepository {
    suspend fun getProducts() : Result<List<Product>>
}