package com.example.proyectofinal_2025a
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nombreEditText = findViewById<EditText>(R.id.editTextNombre)
        val deportesCheckBox = findViewById<CheckBox>(R.id.checkBoxDeportes)
        val musicaCheckBox = findViewById<CheckBox>(R.id.checkBoxMusica)
        val hobbiesCheckBox = findViewById<CheckBox>(R.id.checkBoxHobbies)
        val botonSiguiente = findViewById<Button>(R.id.botonSiguiente)

        botonSiguiente.setOnClickListener {
            val nombre = nombreEditText.text.toString()
            val preferencias = mutableListOf<String>()
            if (deportesCheckBox.isChecked) preferencias.add("Deportes")
            if (musicaCheckBox.isChecked) preferencias.add("Música")
            if (hobbiesCheckBox.isChecked) preferencias.add("Hobbies")

            val intent = Intent(this, JuegoActividad::class.java)
            intent.putExtra("nombre", nombre)
            intent.putExtra("preferencias", preferencias.toTypedArray())
            startActivity(intent)
        }
    }
}