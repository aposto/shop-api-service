package org.shop.shopapiservice.application.port.usecase

import org.shop.shopapiservice.domain.BrandDetail
import org.shop.shopapiservice.domain.CommandResult

interface BrandCommand {
    suspend fun createBrand(brandName: String): BrandDetail?

    suspend fun updateBrand(brandId: Int, newName: String): CommandResult

    suspend fun deleteBrand(brandId: Int): CommandResult
}
