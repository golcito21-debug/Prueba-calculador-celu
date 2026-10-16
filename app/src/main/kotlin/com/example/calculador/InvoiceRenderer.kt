package com.example.calculador

import java.awt.Color
import java.awt.Font
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object InvoiceRenderer {
    fun generatePng(invoice: ProductInvoice, outputFile: File) {
        val width = 780
        val height = 420
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val graphics = image.createGraphics()
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        graphics.paint = Color.WHITE
        graphics.fillRect(0, 0, width, height)

        graphics.color = Color.DARK_GRAY
        graphics.font = Font("SansSerif", Font.BOLD, 28)
        graphics.drawString("Comprobante", 50, 60)

        graphics.color = Color.BLACK
        graphics.font = Font("SansSerif", Font.PLAIN, 22)
        val lines = listOf(
            "Producto: ${invoice.product}",
            "Cantidad: ${invoice.quantity}",
            "Precio unitario: $${invoice.unitPrice.setScale(2).toPlainString()}",
            "Total: $${invoice.total().toPlainString()}"
        )

        lines.forEachIndexed { index, line ->
            graphics.drawString(line, 50, 110 + index * 56)
        }

        graphics.dispose()
        ImageIO.write(image, "png", outputFile)
    }

    fun generatePdf(invoice: ProductInvoice, outputFile: File) {
        val total = invoice.total().setScale(2).toPlainString()
        val content = """
%PDF-1.4
1 0 obj
<< /Type /Catalog /Pages 2 0 R >>
endobj
2 0 obj
<< /Type /Pages /Kids [3 0 R] /Count 1 >>
endobj
3 0 obj
<< /Type /Page /Parent 2 0 R /MediaBox [0 0 595 842] /Contents 4 0 R /Resources << /Font << /F1 5 0 R >> >> >>
endobj
4 0 obj
<< /Length 168 >>
stream
BT
/F1 24 Tf
50 780 Td
(Comprobante) Tj
0 -40 Td
/F1 16 Tf
(Producto: ${invoice.product}) Tj
0 -35 Td
(Cantidad: ${invoice.quantity}) Tj
0 -35 Td
(Precio unitario: \$${invoice.unitPrice.setScale(2).toPlainString()}) Tj
0 -35 Td
(Total: \$${total}) Tj
ET
endstream
endobj
5 0 obj
<< /Type /Font /Subtype /Type1 /BaseFont /Helvetica >>
endobj
xref
0 6
0000000000 65535 f 
0000000010 00000 n 
0000000062 00000 n 
0000000123 00000 n 
0000000246 00000 n 
0000000418 00000 n 
trailer
<< /Root 1 0 R /Size 6 >>
startxref
494
%%EOF
""".trimIndent()
        outputFile.writeBytes(content.toByteArray(Charsets.ISO_8859_1))
    }
}
