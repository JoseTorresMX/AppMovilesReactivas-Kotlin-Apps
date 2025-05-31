package com.example.proyectofinal_2025a

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class JuegoActividad : AppCompatActivity() {
    private val preguntas = arrayOf(
        "¿Cuál es la capital de Francia?",
        "¿Cuánto es 5 x 5?",
        "¿Cuál es el lenguaje principal de Android?"
    )
    private val respuestas = arrayOf("Paris", "25", "Kotlin")

    private var preguntaIndex = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        val preguntaTextView = findViewById<TextView>(R.id.textViewPregunta)
        val respuestaEditText = findViewById<EditText>(R.id.editTextRespuesta)
        val botonValidar = findViewById<Button>(R.id.botonValidar)
        val botonContinuar = findViewById<Button>(R.id.botonContinuar)

        preguntaIndex = (preguntas.indices).random()
        preguntaTextView.text = preguntas[preguntaIndex]

        botonValidar.setOnClickListener {
            val respuestaUsuario = respuestaEditText.text.toString()
            if (respuestaUsuario.equals(respuestas[preguntaIndex], ignoreCase = true)) {
                Toast.makeText(this, "Respuesta correcta!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Respuesta incorrecta", Toast.LENGTH_SHORT).show()
            }
        }

        botonContinuar.setOnClickListener {
            val nombre = intent.getStringExtra("nombre")
            val preferencias = intent.getStringArrayExtra("preferencias")

            val intentResumen = Intent(this, ResumenActividad::class.java)
            intentResumen.putExtra("nombre", nombre)
            intentResumen.putExtra("preferencias", preferencias)
            intentResumen.putExtra("pregunta", preguntas[preguntaIndex])
            intentResumen.putExtra("respuesta", respuestas[preguntaIndex])
            startActivity(intentResumen)
        }
    }
}