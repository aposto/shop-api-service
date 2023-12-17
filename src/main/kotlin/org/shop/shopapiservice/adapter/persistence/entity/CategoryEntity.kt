package org.shop.shopapiservice.adapter.persistence.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table


@Table(name = "categories")
data class CategoryEntity(
    @Id
    val id: Int,
    val name: String,
)
