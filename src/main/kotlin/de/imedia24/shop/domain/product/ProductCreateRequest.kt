package de.imedia24.shop.domain.product

import java.math.BigDecimal



data class ProductCreateRequest(
        var sku: String,
        var name: String,
        var description: String?,
        var price: BigDecimal
)




