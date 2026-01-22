package com.valtx.bank.domain.product

import com.valtx.bank.domain.repository.ProductRepository
import com.valtx.bank.domain.result.Result
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    suspend operator fun invoke(): Result<List<Product>> {
        return repository.getProducts()
    }
}