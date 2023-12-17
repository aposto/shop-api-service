package org.shop.shopapiservice.adapter.persistence

import kotlinx.coroutines.flow.Flow
import org.shop.shopapiservice.adapter.persistence.entity.BrandTotalEntity
import org.shop.shopapiservice.adapter.persistence.entity.ProductEntity
import org.shop.shopapiservice.adapter.persistence.entity.ProductViewEntity
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.query.Param

interface ProductRepository : CoroutineCrudRepository<ProductEntity, Int> {
    @Query(value = """
        SELECT p.id id, b.name brand, c.name category, p.price price 
        FROM products p
        JOIN brands b ON b.id = p.brand_id 
        JOIN categories c ON c.id = p.category_id 
        WHERE b.name = :name
        """)
    fun findByBrandName(name: String): Flow<ProductViewEntity>

    @Query(value = """
        SELECT c.name category, b.name brand, p.price from products p
        JOIN brands b ON b.id = p.brand_id
        JOIN categories c ON c.id = p.category_id
        WHERE (p.category_id, p.PRICE) IN (SELECT category_id, MIN(price) FROM products GROUP BY category_id) ORDER BY p.category_id
    """)
    fun findMinPriceByCategory(): Flow<ProductViewEntity>

    @Query(value = """
        SELECT brand_id, sum(price) as total
        FROM products
        GROUP BY brand_id
        ORDER BY total LIMIT 1
    """)
    suspend fun getMinPriceBrand(): BrandTotalEntity

    @Query(value = """
        SELECT p.id id, b.name brand, c.name category, p.price price 
        FROM products p
        JOIN brands b ON b.id = p.brand_id 
        JOIN categories c ON c.id = p.category_id 
        WHERE b.id = :brandId 
    """)
    fun findProductByBrandId(brandId: Int): Flow<ProductViewEntity>

    // toto 쿼리가 MIN / MAX만 다르고 나머지가 같다. 추후 R2DBCTemplate으로 동저쿼리 생성 변경 필요
    @Query(value = """
        SELECT c.name CATEGORY, b.NAME brand, p.PRICE from PRODUCTS p
        JOIN brands b ON b.id = p.brand_id
        JOIN categories c ON c.id = p.category_id AND c.name = :category
        WHERE p.PRICE = (SELECT MIN(price) FROM PRODUCTS WHERE category_id = c.id) AND p.category_id = c.id
    """)
    fun findMinProductByCategory(category: String): Flow<ProductViewEntity>

    // toto 쿼리가 MIN / MAX만 다르고 나머지가 같다. 추후 R2DBCTemplate으로 동저쿼리 생성 변경 필요
    @Query(value = """
        SELECT c.name CATEGORY, b.NAME brand, p.PRICE from PRODUCTS p
        JOIN brands b ON b.id = p.brand_id
        JOIN categories c ON c.id = p.category_id AND c.name = :category
        WHERE p.PRICE = (SELECT MAX(price) FROM PRODUCTS WHERE category_id = c.id) AND p.category_id = c.id
    """)
    fun findMaxProductByCategory(category: String): Flow<ProductViewEntity>

    @Modifying // INSERT INTO products (brand_id, category_id, price) VALUES (9, 1, 11400);
    @Query(value = "INSERT INTO products(brand_id, category_id, price) VALUES(:brandId, :categoryId, :price)")  // LAST_INSERT_ID
    suspend fun insert(brandId: Int, categoryId: Int, price: Int): Long

    @Query(value = "SELECT LAST_INSERT_ID() AS id" )
    suspend fun lastInsertId(): Int

}
