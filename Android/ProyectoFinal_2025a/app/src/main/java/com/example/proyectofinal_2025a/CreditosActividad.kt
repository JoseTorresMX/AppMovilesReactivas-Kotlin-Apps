package com.example.proyectofinal_2025a

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CreditosActividad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creditos)

        val botonVolver = findViewById<Button>(R.id.botonVolver)
        botonVolver.setOnClickListener {
            finish()
        }
    }
}
