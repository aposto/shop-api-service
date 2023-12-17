package org.shop.shopapiservice.adapter.persistence

import org.shop.shopapiservice.adapter.persistence.entity.BrandEntity
import org.shop.shopapiservice.application.port.outbound.ModifyBrandPort
import org.shop.shopapiservice.domain.BrandDetail
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class BrandH2Adapter(private val repo: BrandRepository) : ModifyBrandPort {
    @Transactional
    override suspend fun insertBrand(brandName: String): BrandDetail? {
        repo.insert(brandName)
        //repo.lastInsertId()
        return BrandDetail(repo.findByName(brandName)?.id ?: 0, brandName)
    }

    @Transactional
    override suspend fun saveBrand(brandId: Int, newName: String): String? {
        return repo.save(BrandEntity(brandId, newName))
            .let { null }
    }

    @Transactional
    override suspend fun deleteBrand(brandId: Int) =
        repo.deleteById(brandId)
}
