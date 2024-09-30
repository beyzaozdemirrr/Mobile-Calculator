package com.beyza.hesapmakinesii

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    lateinit var tvResult: TextView
    var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult = findViewById(R.id.tvResult)

        // Butonların referanslarını alıyoruz
        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnPlus, R.id.btnMinus,
            R.id.btnMultiply, R.id.btnDivide, R.id.btnOpenParen, R.id.btnCloseParen, R.id.btnDot
        )

        // Her buton için tıklama olaylarını ayarlıyoruz
        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { appendToExpression((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculate() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearExpression() }
    }

    // İfadenin sonuna karakter ekleme
    private fun appendToExpression(value: String) {
        expression += value
        tvResult.text = expression
    }

    // İfadeyi temizleme
    private fun clearExpression() {
        expression = ""
        tvResult.text = "0"
    }

    // Hesaplama işlemi
    private fun calculate() {
        try {
            val result = ExpressionBuilder(expression).build().evaluate()
            tvResult.text = result.toString()
            expression = result.toString() // Sonucu ifade olarak yeniden ayarlama
        } catch (e: Exception) {
            tvResult.text = "Hata"
        }
    }
}
