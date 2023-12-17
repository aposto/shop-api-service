package org.shop.shopapiservice.adapter.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import jakarta.persistence.GeneratedValue
@Table(name = "brands")
data class BrandEntity(
    @Id
    @GeneratedValue
    val id: Int,
    val name: String,
)

data class BrandTotalEntity(val brandId: Int, val total: Int)
