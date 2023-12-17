package org.shop.shopapiservice.application.service

import kotlinx.coroutines.flow.toList
import org.shop.shopapiservice.application.port.outbound.LoadCategoryProduct
import org.shop.shopapiservice.application.port.usecase.CategoryProductUseCase
import org.shop.shopapiservice.domain.MinMaxProductByCategory
import org.shop.shopapiservice.domain.MinPriceBrandPerCategory
import org.springframework.stereotype.Component

@Component
class CategoryProductService(
    private val loadCategoryProduct: LoadCategoryProduct
) : CategoryProductUseCase {
    override suspend fun getMinPriceBrandPerCategory(): MinPriceBrandPerCategory {
        // 최저가 시 같은 카테고리 동일 가격시 중복이 됨
        val products = loadCategoryProduct.findMinPriceByCategory().toList().distinctBy { it.category }
        return MinPriceBrandPerCategory(products = products, total = products.sumOf{ it.price })
    }

    override suspend fun getMinMaxProductByCategory(categoryName: String): MinMaxProductByCategory {
        val minProducts = loadCategoryProduct.findMinMaxProductByCategoryId(true, category = categoryName).toList()
        val maxProducts = loadCategoryProduct.findMinMaxProductByCategoryId(false, category = categoryName).toList()

        return MinMaxProductByCategory(category = categoryName, minPrice = minProducts, maxPrice = maxProducts)
    }
}
