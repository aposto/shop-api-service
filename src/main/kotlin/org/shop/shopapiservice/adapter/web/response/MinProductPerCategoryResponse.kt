package org.shop.shopapiservice.adapter.web.response

import com.fasterxml.jackson.annotation.JsonProperty


data class MinProductPerCategoryResponse(
    val success: Boolean,
    val error: String?,
    val items: List<MinProductPerCategoryItemDto> = emptyList(),
    @JsonProperty("총액")
    val total: String? = null,
)

data class MinProductPerCategoryItemDto(
    @JsonProperty("카테고리")
    val category: String,
    @JsonProperty("브랜드")
    val brand: String,
    @JsonProperty("가격")
    val price: String,
)
