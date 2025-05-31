package com.example.practica01
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etNum1: EditText
    private lateinit var etNum2: EditText
    private lateinit var btnSumar: Button
    private lateinit var btnRestar: Button
    private lateinit var btnMultiplicar: Button
    private lateinit var btnDividir: Button
    private lateinit var btnResiduo: Button
    private lateinit var btnBorrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        btnSumar = findViewById(R.id.btnSumar)
        btnRestar = findViewById(R.id.btnRestar)
        btnMultiplicar = findViewById(R.id.btnMultiplicar)
        btnDividir = findViewById(R.id.btnDividir)
        btnResiduo = findViewById(R.id.btnResiduo)
        btnBorrar = findViewById(R.id.btnBorrar)

        btnSumar.setOnClickListener { operar("sumar") }
        btnRestar.setOnClickListener { operar("restar") }
        btnMultiplicar.setOnClickListener { operar("multiplicar") }
        btnDividir.setOnClickListener { operar("dividir") }
        btnResiduo.setOnClickListener { operar("residuo") }
        btnBorrar.setOnClickListener { borrarCampos() }
    }

    private fun operar(operacion: String) {
        val num1Text = etNum1.text.toString()
        val num2Text = etNum2.text.toString()

        if (num1Text.isEmpty() || num2Text.isEmpty()) {
            Toast.makeText(this, "Por favor ingresa ambos números", Toast.LENGTH_SHORT).show()
            return
        }

        val num1 = num1Text.toDoubleOrNull()
        val num2 = num2Text.toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Por favor ingresa números válidos", Toast.LENGTH_SHORT).show()
            return
        }

        val resultado: Double = when (operacion) {
            "sumar" -> num1 + num2
            "restar" -> num1 - num2
            "multiplicar" -> num1 * num2
            "dividir" -> {
                val mayor = maxOf(num1, num2)
                val menor = minOf(num1, num2)
                if (menor == 0.0) {
                    Toast.makeText(this, "La división por cero no se puede realizar", Toast.LENGTH_SHORT).show()
                    return
                }
                mayor / menor
            }
            "residuo" -> {
                val mayor = maxOf(num1, num2)
                val menor = minOf(num1, num2)
                if (menor == 0.0) {
                    Toast.makeText(this, "La división por cero no se puede realizar", Toast.LENGTH_SHORT).show()
                    return
                }
                mayor % menor
            }
            else -> 0.0
        }

        Toast.makeText(this, "Resultado: $resultado", Toast.LENGTH_LONG).show()
        borrarCampos()
    }

    private fun borrarCampos() {
        etNum1.text.clear()
        etNum2.text.clear()
        etNum1.requestFocus()
    }
}
