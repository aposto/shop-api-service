package org.shop.shopapiservice.domain

data class MinMaxProductByCategory(
    val category: String,
    val minPrice: List<Product>,
    val maxPrice: List<Product>,
)

