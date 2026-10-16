package com.example.calculador

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculador.databinding.ActivityMainBinding
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            val productName = binding.productInput.text?.toString()?.trim().orEmpty()
            val quantityText = binding.quantityInput.text?.toString()?.trim().orEmpty()
            val priceText = binding.priceInput.text?.toString()?.trim().orEmpty()

            if (productName.isBlank() || quantityText.isBlank() || priceText.isBlank()) {
                Toast.makeText(this, getString(R.string.empty_error), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val quantity = quantityText.toIntOrNull()
            if (quantity == null || quantity <= 0) {
                Toast.makeText(this, getString(R.string.invalid_quantity), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val price = priceText.toBigDecimalOrNull()
            if (price == null || price <= BigDecimal.ZERO) {
                Toast.makeText(this, getString(R.string.invalid_price), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val total = InvoiceCalculator.calculateTotal(quantity, price)
            binding.totalTextView.text = getString(R.string.total_format, "$${total.setScale(2).toPlainString()}")

            val pdfFile = InvoicePdfExporter.createPdf(this, productName, quantity, price, total)
            binding.pdfStatusTextView.text = "PDF generado: ${pdfFile.name}"
            Toast.makeText(this, getString(R.string.pdf_saved), Toast.LENGTH_SHORT).show()
        }
    }
}
