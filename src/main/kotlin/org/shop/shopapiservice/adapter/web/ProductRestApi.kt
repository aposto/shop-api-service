package org.shop.shopapiservice.adapter.web

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.shop.shopapiservice.adapter.web.response.*
import org.shop.shopapiservice.application.port.usecase.BrandProductUseCase
import org.shop.shopapiservice.application.port.usecase.CategoryProductUseCase
import org.shop.shopapiservice.domain.Product
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("product/v1/")
class ProductRestApi
    (
    private val categoryProductUseCase: CategoryProductUseCase,
    private val brandProductUseCase: BrandProductUseCase
) {
    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "카테고리 별 최저가격 브랜드와 상품 가격, 총액을 조회하는 API")])
    @GetMapping("/min-brand-per-category")
    @ResponseBody
    suspend fun getMinBrandPerCategory(response: ServerHttpResponse): MinProductPerCategoryResponse {
        val result = categoryProductUseCase.getMinPriceBrandPerCategory()
        return try {
            MinProductPerCategoryResponse(
                success = true,
                error = null,
                items = result.products.map { MinProductPerCategoryItemDto(it.category, it.brand, it.price.toMoney()) },
                total = result.total.toMoney(),
            )
        } catch (t: Throwable) {
            // response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            MinProductPerCategoryResponse(success = false, error = t.message ?: "INTERNAL_SERVER_ERROR")
        }
    }

    @ApiResponses(
        value = [ApiResponse(
            responseCode = "200",
            description = "단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액을 조회하는 API"
        )]
    )
    @GetMapping("/brand-product")
    @ResponseBody
    suspend fun getBrandProductByMin(response: ServerHttpResponse): BrandMinProductResponse {
        val result = brandProductUseCase.getBrandProductByMin()
        return try {
            BrandMinProductResponse(
                success = true,
                error = null,
                minBrandProduct = MinBrandProductItemDto(
                    brand = result.brand,
                    categories = result.products.map { CategoryItemDto(it.category, it.price.toMoney()) },
                    total = result.total.toMoney(),
                ),
            )
        } catch (t: Throwable) {
            // response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            BrandMinProductResponse(success = false, error = t.message ?: "INTERNAL_SERVER_ERROR")
        }
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격을 조회하는 API")])
    @GetMapping("/min-max-product/{category}")
    @ResponseBody
    suspend fun getMinMaxProductByCategory(
        @PathVariable("category") category: String,
        response: ServerHttpResponse
    ): MinMaxProductResponse {
        require(category.isNotEmpty())
        return try {
            val result = categoryProductUseCase.getMinMaxProductByCategory(category)
            val domainToMinBrandPriceItemDto = { it: Product -> BrandPriceItemDto(it.brand, it.price.toMoney()) }
            if (result.minPrice.isNotEmpty() && result.maxPrice.isNotEmpty()) {
                MinMaxProductResponse(
                    success = true,
                    error = null,
                    category = category,
                    minBrandProduct = result.minPrice.map(domainToMinBrandPriceItemDto),
                    maxBrandProduct = result.maxPrice.map(domainToMinBrandPriceItemDto)
                )
            } else {
                MinMaxProductResponse(success = false, error = "INVALID_CATEGORY", category = category)
            }
        } catch (t: Throwable) {
            // response.statusCode = HttpStatus.INTERNAL_SERVER_ERROR
            MinMaxProductResponse(success = false, error = t.message ?: "INTERNAL_SERVER_ERROR", category = category)
        }
    }
}

