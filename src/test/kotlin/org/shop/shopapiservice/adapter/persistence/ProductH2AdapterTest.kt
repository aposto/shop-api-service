package org.shop.shopapiservice.adapter.persistence

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.flow.toList
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ProductH2AdapterTest(private val adapter: ProductH2Adapter) : FunSpec({
    val allCategorySize = 8
    val allBrandSize = 9

    test("findMinPriceByCategory") {
        val products = adapter.findMinPriceByCategory().toList()
        products.forEach {
            println(it)
        }
        products.size shouldBe allBrandSize
        products.distinctBy { it.category }.sumOf { it.price } shouldBe 34100
    }

    test("getMinBriceBrand") {
        adapter.findMinPriceBrand() shouldBe 4
    }
    test("brand price per category") {
        val products = adapter.findBrandCategory(4).toList()
        products.size shouldBe allCategorySize
        products.sumOf { it.price } shouldBe 36100
    }

    test("category min/max product") {
        val products0 = adapter.findMinMaxProductByCategoryId(true, "상의").toList()
        val products1 = adapter.findMinMaxProductByCategoryId(false, "상의").toList()
        products0.size shouldBeGreaterThan 0
        products1.size shouldBeGreaterThan 0
        products0[0].brand shouldBe "C"
        products0[0].price shouldBe 10000

        products1[0].brand shouldBe "I"
        products1[0].price shouldBe 11400
    }
})
