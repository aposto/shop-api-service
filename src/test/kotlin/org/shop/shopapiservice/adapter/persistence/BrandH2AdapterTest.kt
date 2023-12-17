package org.shop.shopapiservice.adapter.persistence

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import org.shop.shopapiservice.domain.commandFail
import org.shop.shopapiservice.domain.commandOk
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class BrandH2AdapterTest(val adapter: BrandH2Adapter) : FunSpec({

    test("createBrand") {
        adapter.createBrand("SIMPLE") shouldBe commandOk()
        adapter.createBrand("SIMPLE") shouldBe commandFail("DUPLICATED")
    }

    test("updateBrand") {
        adapter.updateBrand(1, "AA") shouldBe commandOk()
        adapter.updateBrand(2, "SIMPLE") shouldBe commandFail("DUPLICATED")
    }

    test("deleteBrand") {
        adapter.deleteBrand(1) shouldBe commandOk()
        adapter.deleteBrand(200) shouldBe commandOk()
    }
})
