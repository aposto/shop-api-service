package org.shop.shopapiservice.application.port.usecase

import org.shop.shopapiservice.domain.BrandMinProducts

interface BrandProductUseCase {
    /*
    단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액
     */
    suspend fun getBrandProductByMin(): BrandMinProducts
}
