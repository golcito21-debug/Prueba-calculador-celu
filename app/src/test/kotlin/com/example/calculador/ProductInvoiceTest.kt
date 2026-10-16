package com.example.calculador

import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.io.path.createTempDirectory
import kotlin.io.path.writeText
import kotlin.test.assertTrue

class ProductInvoiceTest {
    @Test
    fun `calculates total with quantity and unit price`() {
        val invoice = ProductInvoice("Teclado", 3, BigDecimal("25.50"))
        assertEquals(BigDecimal("76.50"), invoice.total())
    }

    @Test
    fun `creates a png invoice file`() {
        val invoice = ProductInvoice("Mouse", 2, BigDecimal("19.99"))
        val tempDir = createTempDirectory("invoice-test")
        val output = tempDir.resolve("mouse.png").toFile()

        InvoiceRenderer.generatePng(invoice, output)

        assertTrue(output.exists())
        assertTrue(output.length() > 0)
    }

    @Test
    fun `creates a pdf invoice file`() {
        val invoice = ProductInvoice("Monitor", 1, BigDecimal("350.00"))
        val tempDir = createTempDirectory("invoice-test")
        val output = tempDir.resolve("monitor.pdf").toFile()

        InvoiceRenderer.generatePdf(invoice, output)

        assertTrue(output.exists())
        assertTrue(output.length() > 0)
    }
}
