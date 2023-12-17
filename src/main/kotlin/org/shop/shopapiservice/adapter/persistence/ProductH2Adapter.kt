package org.shop.shopapiservice.adapter.persistence

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.shop.shopapiservice.adapter.persistence.entity.ProductEntity
import org.shop.shopapiservice.adapter.persistence.entity.ProductViewEntity
import org.shop.shopapiservice.application.port.outbound.LoadBrandProduct
import org.shop.shopapiservice.application.port.outbound.LoadCategoryProduct
import org.shop.shopapiservice.application.port.outbound.ModifyProductPort
import org.shop.shopapiservice.domain.Product
import org.shop.shopapiservice.domain.ProductCore
import org.springframework.stereotype.Component

@Component
class ProductH2Adapter(private val repo: ProductRepository) :
    LoadCategoryProduct, LoadBrandProduct, ModifyProductPort {
    override fun findMinPriceByCategory(): Flow<Product> {
        return repo.findMinPriceByCategory()
            .map(::mapToDomain)
    }

    override suspend fun findMinPriceBrand(): Int {
        return repo.getMinPriceBrand().brandId
    }

    override fun findBrandCategory(brandId: Int): Flow<Product> {
        return repo.findProductByBrandId(brandId)
            .map(::mapToDomain)
    }

    override fun findMinMaxProductByCategoryId(isMin: Boolean, category: String): Flow<Product> {
        return if (isMin)
            repo.findMinProductByCategory(category).map(::mapToDomain)
        else
            repo.findMaxProductByCategory(category).map(::mapToDomain)
    }

    override suspend fun insertProduct(newProduct: ProductCore): ProductCore? {
        repo.insert(newProduct.brandId, newProduct.categoryId, newProduct.price)
        return ProductCore(0, newProduct.brandId, newProduct.categoryId, newProduct.price)
    }

    override suspend fun saveProduct(product: ProductCore): String? {
        return repo.save(ProductEntity(
            id = product.id!!,
            brandId = product.brandId,
            categoryId = product.categoryId,
            price = product.price))
            .let { null }
    }

    override suspend fun deleteProduct(id: Int) {
        return repo.deleteById(id)
    }

    private fun mapToDomain(e: ProductViewEntity) =
        Product(brand = e.brand, category = e.category, price = e.price)

}
