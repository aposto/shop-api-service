package org.shop.shopapiservice.application.service

import org.shop.shopapiservice.application.port.outbound.ModifyBrandPort
import org.shop.shopapiservice.application.port.usecase.BrandCommand
import org.shop.shopapiservice.domain.BrandDetail
import org.shop.shopapiservice.domain.CommandResult
import org.shop.shopapiservice.domain.commandOk
import org.springframework.stereotype.Component

@Component
class BrandCommandService(private val modifyBrandPort: ModifyBrandPort) : BrandCommand {
    override suspend fun createBrand(brandName: String): BrandDetail? {
        // @todo 신규 이름 중복 검사
        return modifyBrandPort.insertBrand(brandName)
    }

    override suspend fun updateBrand(brandId: Int, newName: String): CommandResult {
        // @todo brandId 검사, 변경 이름 중복 검사
        return CommandResult(modifyBrandPort.saveBrand(brandId, newName))
    }

    override suspend fun deleteBrand(brandId: Int): CommandResult {
        // @todo brandId 존재 유무
        modifyBrandPort.deleteBrand(brandId)
        return commandOk()
    }
}
