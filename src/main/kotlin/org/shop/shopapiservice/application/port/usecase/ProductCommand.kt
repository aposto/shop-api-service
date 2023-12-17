package org.shop.shopapiservice.application.port.usecase

import org.shop.shopapiservice.domain.CommandResult
import org.shop.shopapiservice.domain.ProductCore

interface ProductCommand {
    suspend fun createProduct(newProduct: ProductCore): ProductCore?

    suspend fun updateProduct(product: ProductCore): CommandResult

    suspend fun deleteProduct(productId: Int): CommandResult
}

