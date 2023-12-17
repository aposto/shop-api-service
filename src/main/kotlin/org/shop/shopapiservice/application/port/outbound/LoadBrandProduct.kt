package org.shop.shopapiservice.application.port.outbound

import kotlinx.coroutines.flow.Flow
import org.shop.shopapiservice.domain.Product

interface LoadBrandProduct {
    suspend fun findMinPriceBrand(): Int

    fun findBrandCategory(brandId: Int): Flow<Product>

}
