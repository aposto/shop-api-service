package org.shop.shopapiservice.adapter.web.request

data class ModifyProductRequest(val id: Int?, val brandId: Int, val categoryId: Int, val price: Int)

