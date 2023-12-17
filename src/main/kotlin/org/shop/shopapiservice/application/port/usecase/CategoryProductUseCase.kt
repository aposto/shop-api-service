package org.shop.shopapiservice.application.port.usecase

import org.shop.shopapiservice.domain.MinMaxProductByCategory
import org.shop.shopapiservice.domain.MinPriceBrandPerCategory

interface CategoryProductUseCase {

    /*
    카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회
     */
    suspend fun getMinPriceBrandPerCategory(): MinPriceBrandPerCategory

    /*
    카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회
     */
    suspend fun getMinMaxProductByCategory(categoryName: String): MinMaxProductByCategory
}
