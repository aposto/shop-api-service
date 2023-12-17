package org.shop.shopapiservice.domain

data class MinPriceBrandPerCategory(
    val products: List<Product>,
    val total: Int
)
