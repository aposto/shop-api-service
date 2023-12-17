package org.shop.shopapiservice.domain

data class BrandMinProducts(
    val brand: String,
    val products: List<Product>,
    val total: Int,
)
