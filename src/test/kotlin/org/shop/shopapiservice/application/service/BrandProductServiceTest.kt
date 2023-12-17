package org.shop.shopapiservice.application.service

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@DisplayName("브랜드 최저가 유스케이스")
class BrandProductServiceTest(val brandProductUseCase: BrandProductService) : BehaviorSpec({

    Given("단일 브랜드로 모든 카테고리 상품을 구매할 때 최저가격에 판매하는 브랜드와 카테고리의 상품가격, 총액") {
        When("getBrandProductByMin") {
            val result = brandProductUseCase.getBrandProductByMin()
            Then("브랜드 D") {
                result.brand shouldBe "D"
            }
            Then("총액 36100") {
                result.total shouldBe 36100
            }
        }
    }
})
