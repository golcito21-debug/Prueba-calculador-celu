package com.example.calculador

import java.math.BigDecimal
import java.math.RoundingMode

data class ProductInvoice(
    val product: String,
    val quantity: Int,
    val unitPrice: BigDecimal
) {
    init {
        require(product.isNotBlank()) { "El producto no puede estar vacío." }
        require(quantity > 0) { "La cantidad debe ser mayor a cero." }
        require(unitPrice > BigDecimal.ZERO) { "El precio debe ser mayor a cero." }
    }

    fun total(): BigDecimal {
        return BigDecimal(quantity)
            .multiply(unitPrice)
            .setScale(2, RoundingMode.HALF_UP)
    }
}
