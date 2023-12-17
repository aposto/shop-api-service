package org.shop.shopapiservice.application.service

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
@DisplayName("카테고리별 가격 정보 유스케이스")
class CategoryProductServiceTest(val categoryProductUseCase: CategoryProductService) : BehaviorSpec({

    Given("카테고리 별 최저가격 브랜드와 상품 가격") {
        When("getMinPriceBrandPerCategory() 정상처리") {
            val result = categoryProductUseCase.getMinPriceBrandPerCategory()
            Then("총액 341200") {
                result.total shouldBe 34100
            }
            Then("상의 C브랜드 / 10,000 ") {
                result.products[0].price shouldBe 10000
                result.products[0].brand shouldBe  "C"
            }
        }
    }

    Given("카테고리 이름으로 최저, 최고 가격 브랜드와 상품 가격") {
        When("getMinMaxProductByCategory 정상처리") {
            val result = categoryProductUseCase.getMinMaxProductByCategory("상의")
            Then("카테고리 \"상의\"") {
                result.category shouldBe "상의"
            }
            Then("최저가 C브랜드 10,000") {
                result.minPrice[0].brand shouldBe "C"
                result.minPrice[0].price shouldBe 10000
            }
            Then("최고가 I브랜드 11,400") {
                result.maxPrice[0].brand shouldBe "I"
                result.maxPrice[0].price shouldBe 11400
            }
        }
    }
})
