package com.example.calculador

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigDecimal

class InvoiceCalculatorTest {
    @Test
    fun `calculateTotal multiplies quantity by price`() {
        val total = InvoiceCalculator.calculateTotal(3, BigDecimal("25.50"))
        assertEquals(BigDecimal("76.50"), total)
    }

    @Test
    fun `calculateTotal rounds to two decimals`() {
        val total = InvoiceCalculator.calculateTotal(2, BigDecimal("10.005"))
        assertEquals(BigDecimal("20.01"), total)
    }
}
