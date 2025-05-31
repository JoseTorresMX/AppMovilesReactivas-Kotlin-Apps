package com.example.practica02
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumero1 = findViewById<EditText>(R.id.etNumero1)
        val etNumero2 = findViewById<EditText>(R.id.etNumero2)
        val btnSumar = findViewById<Button>(R.id.btnSumar)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)

        btnSumar.setOnClickListener {
            try {
                val num1 = etNumero1.text.toString().toDouble()
                val num2 = etNumero2.text.toString().toDouble()
                val resultado = num1 + num2
                tvResultado.text = "Resultado: $resultado"
            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Por favor ingresa números válidos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}