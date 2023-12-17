package org.shop.shopapiservice.application.service

import kotlinx.coroutines.flow.toList
import org.shop.shopapiservice.application.port.outbound.LoadBrandProduct
import org.shop.shopapiservice.application.port.usecase.BrandProductUseCase
import org.shop.shopapiservice.domain.BrandMinProducts
import org.springframework.stereotype.Component

@Component
class BrandProductService(private val loadBrandProduct: LoadBrandProduct) : BrandProductUseCase {
    override suspend fun getBrandProductByMin(): BrandMinProducts {
        val brandId = loadBrandProduct.findMinPriceBrand()
        val products = loadBrandProduct.findBrandCategory(brandId).toList()
        check(products.isNotEmpty())

        return BrandMinProducts(brand = products[0].brand, products = products, total = products.sumOf { it.price })
    }
}
