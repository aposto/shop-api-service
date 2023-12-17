package org.shop.shopapiservice.adapter.web

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.shop.shopapiservice.adapter.web.request.ModifyProductRequest
import org.shop.shopapiservice.application.port.usecase.ProductCommand
import org.shop.shopapiservice.domain.*
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin/v1/product")
class AdminProductRestApi(
    private val productCommand: ProductCommand,
) {
    @ApiResponses(value = [ApiResponse(responseCode = "201", description = "상품 신규 생성")])
    @PostMapping
    @ResponseBody
    suspend fun createBrand(
        @RequestBody req: ModifyProductRequest,
        response: ServerHttpResponse
    ): CommandBodyResult<ProductCore> {
        return try {
            productCommand.createProduct(
                ProductCore(id = null, brandId = req.brandId, categoryId = req.categoryId, price = req.price)
            )
                ?.let { commandBodyOk(it) }
                ?: commandBodyFail("DUPLICATED")
        } catch (t: Throwable) {
            commandBodyFail(t.message ?: "UNKNOWN")
        }
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "상품 정보 수정")])
    @PutMapping("/{productId}")
    @ResponseBody
    suspend fun updateBrand(
        @PathVariable("productId") productId: Int, @RequestBody req: ModifyProductRequest,
    ): CommandBodyResult<Boolean> {
        return try {
            commandBodyOk(
                productCommand.updateProduct(
                    ProductCore(id = productId, brandId = req.brandId, categoryId = req.categoryId, price = req.price)
                ).success
            )
        } catch (t: Throwable) {
            commandBodyFail(t.message ?: "UNKNOWN")
        }
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "상품 삭제")])
    @DeleteMapping("/{productId}")
    @ResponseBody
    suspend fun deleteBrand(
        @PathVariable("productId") productId: Int,
    ): CommandBodyResult<Boolean> {
        return try {
            commandBodyOk(productCommand.deleteProduct(productId).success)
        } catch (t: Throwable) {
            commandBodyFail(t.message ?: "UNKNOWN")
        }
    }
}
