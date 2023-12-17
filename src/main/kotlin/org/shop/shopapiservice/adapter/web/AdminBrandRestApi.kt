package org.shop.shopapiservice.adapter.web

import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.shop.shopapiservice.adapter.web.request.ModifyBrandRequest
import org.shop.shopapiservice.application.port.usecase.BrandCommand
import org.shop.shopapiservice.domain.*
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("admin/v1/brand")
class AdminBrandRestApi(
    private val brandCommand: BrandCommand,
) {
    @ApiResponses(value = [ApiResponse(responseCode = "201", description = "브랜드 신규 생성")])
    @PostMapping
    @ResponseBody
    suspend fun createBrand(
        @RequestBody brand: ModifyBrandRequest,
        response: ServerHttpResponse
    ): CommandBodyResult<BrandDetail> {
        return try {
            brandCommand.createBrand(brand.brandName)
                ?.let { commandBodyOk(it) }
                ?: commandBodyFail("DUPLICATED")
        } catch (t: Throwable) {
            commandBodyFail(t.message ?: "UNKNOWN")
        }
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "브랜드 수정")])
    @PutMapping("/{brandId}")
    @ResponseBody
    suspend fun updateBrand(
        @PathVariable("brandId") brandId: Int,
        @RequestBody brand: ModifyBrandRequest
    ): CommandBodyResult<Boolean> {
        return try {
            // @todo 이름 유효성 검사, 중복 검사
            commandBodyOk(brandCommand.updateBrand(brandId, brand.brandName).success)
        } catch (t: Throwable) {
            commandBodyFail(t.message ?: "UNKNOWN")
        }
    }

    @ApiResponses(value = [ApiResponse(responseCode = "200", description = "브랜드 삭제")])
    @DeleteMapping("/{brandId}")
    @ResponseBody
    suspend fun deleteBrand(@PathVariable("brandId") brandId: Int): CommandBodyResult<Boolean> {
        return try {
            commandBodyOk(brandCommand.deleteBrand(brandId).success)
        } catch (t: Throwable) {
            commandBodyFail(t.message ?: "UNKNOWN")
        }
    }
}
