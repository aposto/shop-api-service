package org.shop.shopapiservice.application.port.outbound

import org.shop.shopapiservice.domain.ProductCore

interface ModifyProductPort {
    suspend fun insertProduct(newProduct: ProductCore): ProductCore?

    suspend fun saveProduct(product: ProductCore): String?

    suspend fun deleteProduct(productId: Int)
}
