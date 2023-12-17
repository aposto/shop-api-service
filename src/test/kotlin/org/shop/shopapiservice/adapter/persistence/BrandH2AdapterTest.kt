package org.shop.shopapiservice.adapter.persistence

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DuplicateKeyException

@SpringBootTest
class BrandH2AdapterTest(val adapter: BrandH2Adapter) : FunSpec({

    test("createBrand") {
        adapter.insertBrand("SIMPLE") shouldNotBe null
        shouldThrow<DuplicateKeyException> {
            adapter.insertBrand("SIMPLE")
        }
    }

    test("updateBrand") {
        adapter.saveBrand(1, "AA") shouldBe null
        shouldThrow<DuplicateKeyException> {
            adapter.saveBrand(2, "SIMPLE")
        }
    }

})
