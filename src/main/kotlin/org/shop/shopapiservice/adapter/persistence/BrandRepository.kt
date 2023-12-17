package org.shop.shopapiservice.adapter.persistence

import org.shop.shopapiservice.adapter.persistence.entity.BrandEntity
import org.springframework.data.r2dbc.repository.Modifying
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param

interface BrandRepository : CoroutineCrudRepository<BrandEntity, Int> {
    @Modifying
    @Query(value = "INSERT INTO brands(name) VALUES(:newName)")  // LAST_INSERT_ID
    suspend fun insert(@Param("newName") name: String): Long

    @Query(value = "SELECT SCOPE_IDENTITY()") // SELECT LAST_INSERT_ID() AS id" )
    suspend fun lastInsertId(): Int

    suspend fun findByName(name: String): BrandEntity?
}
