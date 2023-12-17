package org.shop.shopapiservice.adapter.web.response

import com.fasterxml.jackson.annotation.JsonProperty

data class BrandMinProductResponse(
    val success: Boolean,
    val error: String?,

    @JsonProperty("최저가")
    val minBrandProduct: MinBrandProductItemDto? = null

)

data class MinBrandProductItemDto(
    @JsonProperty("브랜드")
    val brand: String,
    @JsonProperty("카테고리")
    val categories: List<CategoryItemDto>,
    @JsonProperty("총액")
    val total: String,
)

data class CategoryItemDto(
    @JsonProperty("카테고리")
    val category: String,
    @JsonProperty("가격")
    val price: String
)
