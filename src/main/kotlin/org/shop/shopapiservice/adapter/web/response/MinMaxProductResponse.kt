package org.shop.shopapiservice.adapter.web.response

import com.fasterxml.jackson.annotation.JsonProperty

data class MinMaxProductResponse(
    val success: Boolean,
    val error: String?,

    @JsonProperty("카테고리")
    val category: String,

    @JsonProperty("최저가")
    val minBrandProduct: List<BrandPriceItemDto>? = emptyList(),
    @JsonProperty("최고가")
    val maxBrandProduct: List<BrandPriceItemDto>? = emptyList(),
)

data class BrandPriceItemDto(
    @JsonProperty("브랜드")
    val brand: String,
    @JsonProperty("가격")
    val price: String,
)
