package org.shop.shopapiservice.adapter.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table(name = "products")
data class ProductEntity(
    @Id
    val id: Int,
    val brandId: Int,
    val categoryId: Int,
    val price: Int,
)
