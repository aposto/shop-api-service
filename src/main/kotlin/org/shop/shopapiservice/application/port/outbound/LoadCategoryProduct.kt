package org.shop.shopapiservice.application.port.outbound

import kotlinx.coroutines.flow.Flow
import org.shop.shopapiservice.domain.Product

interface LoadCategoryProduct {
    fun findMinPriceByCategory(): Flow<Product>

    fun findMinMaxProductByCategoryId(isMin: Boolean, category: String): Flow<Product>
}
