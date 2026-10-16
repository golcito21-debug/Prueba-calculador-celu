package com.example.calculador

import java.io.File
import java.math.BigDecimal

fun main(args: Array<String>) {
    val input = when (args.size) {
        3 -> listOf(args[0], args[1], args[2], "pdf")
        4 -> args.toList()
        else -> {
            print("Producto: ")
            val product = readLine()?.trim() ?: ""

            print("Cantidad: ")
            val quantity = readLine()?.trim()?.toIntOrNull() ?: run {
                println("Cantidad inválida.")
                return
            }

            print("Precio unitario: ")
            val price = readLine()?.trim()?.toBigDecimalOrNull() ?: run {
                println("Precio inválido.")
                return
            }

            print("Formato (png/pdf): ")
            val format = readLine()?.trim()?.lowercase() ?: "pdf"
            listOf(product, quantity.toString(), price.toPlainString(), format)
        }
    }

    val product = input[0].trim()
    val quantity = input[1].toIntOrNull() ?: run {
        println("Cantidad inválida.")
        return
    }
    val price = input[2].toBigDecimalOrNull() ?: run {
        println("Precio inválido.")
        return
    }
    val format = input[3].lowercase()

    val invoice = try {
        ProductInvoice(product, quantity, price)
    } catch (error: IllegalArgumentException) {
        println(error.message)
        return
    }

    val outputFile = File("${invoice.product.replace(" ", "_")}_${System.currentTimeMillis()}.$format")

    when (format) {
        "png" -> InvoiceRenderer.generatePng(invoice, outputFile)
        "pdf" -> InvoiceRenderer.generatePdf(invoice, outputFile)
        else -> {
            println("Formato no soportado. Usa png o pdf.")
            return
        }
    }

    println("Total calculado: $${invoice.total().toPlainString()}")
    println("Archivo generado: ${outputFile.absolutePath}")
}
