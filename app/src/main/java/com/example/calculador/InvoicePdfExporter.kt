package com.example.calculador

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import java.io.File
import java.math.BigDecimal

object InvoicePdfExporter {
    fun createPdf(
        context: Context,
        productName: String,
        quantity: Int,
        price: BigDecimal,
        total: BigDecimal
    ): File {
        val document = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = document.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        val titlePaint = Paint().apply {
            color = Color.BLACK
            textSize = 24f
            isFakeBoldText = true
        }
        val normalPaint = Paint().apply {
            color = Color.BLACK
            textSize = 16f
        }

        canvas.drawText("Comprobante", 60f, 80f, titlePaint)
        canvas.drawText("Producto: $productName", 60f, 140f, normalPaint)
        canvas.drawText("Cantidad: $quantity", 60f, 180f, normalPaint)
        canvas.drawText("Precio unitario: $${price.setScale(2).toPlainString()}", 60f, 220f, normalPaint)
        canvas.drawText("Total: $${total.setScale(2).toPlainString()}", 60f, 280f, normalPaint)

        document.finishPage(page)

        val file = File(context.cacheDir, "comprobante_${System.currentTimeMillis()}.pdf")
        file.outputStream().use { document.writeTo(it) }
        document.close()

        return file
    }
}
