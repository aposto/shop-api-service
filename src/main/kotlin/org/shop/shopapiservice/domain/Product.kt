package org.shop.shopapiservice.domain


data class Product(
    val brand: String,
    val category: String,
    val price: Int,
)

data class ProductCore(
    val id: Int?,
    val brandId: Int,
    val categoryId: Int,
    val price: Int,
)
