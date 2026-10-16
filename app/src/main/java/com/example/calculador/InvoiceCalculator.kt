package com.example.calculador

import java.math.BigDecimal
import java.math.RoundingMode

object InvoiceCalculator {
    private const val MONEY_SCALE = 2

    fun calculateTotal(quantity: Int, price: BigDecimal): BigDecimal {
        return BigDecimal(quantity)
            .multiply(price)
            .setScale(MONEY_SCALE, RoundingMode.HALF_UP)
    }
}
