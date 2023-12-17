package org.shop.shopapiservice.application.service

import org.shop.shopapiservice.application.port.outbound.ModifyProductPort
import org.shop.shopapiservice.application.port.usecase.ProductCommand
import org.shop.shopapiservice.domain.CommandResult
import org.shop.shopapiservice.domain.ProductCore
import org.shop.shopapiservice.domain.commandOk
import org.springframework.stereotype.Component

@Component
class ProductCommandService(private val modifyProductPort: ModifyProductPort) : ProductCommand {
    override suspend fun createProduct(newProduct: ProductCore): ProductCore? {
        // @todo brandId, categoryId, 유요성 검사
        return modifyProductPort.insertProduct(newProduct)
    }

    override suspend fun updateProduct(product: ProductCore): CommandResult {
        // @todo productId, brandId, categoryId, 유요성 검사
        return CommandResult(modifyProductPort.saveProduct(product))
    }

    override suspend fun deleteProduct(productId: Int): CommandResult {
        // @todo brandId 존재 유무
        modifyProductPort.deleteProduct(productId)
        return commandOk()
    }
}
