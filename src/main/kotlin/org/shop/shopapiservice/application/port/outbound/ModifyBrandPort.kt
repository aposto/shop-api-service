package org.shop.shopapiservice.application.port.outbound

import org.shop.shopapiservice.domain.BrandDetail

interface ModifyBrandPort {
    suspend fun insertBrand(brandName: String): BrandDetail?

    suspend fun saveBrand(brandId: Int, newName: String): String?

    suspend fun deleteBrand(brandId: Int)
}
