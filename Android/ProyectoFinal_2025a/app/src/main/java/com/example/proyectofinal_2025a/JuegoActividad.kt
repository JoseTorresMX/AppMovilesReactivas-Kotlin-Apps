package com.example.proyectofinal_2025a

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class JuegoActivity : ComponentActivity() {

    private val preguntas = arrayOf(
        "¿Cuál es la capital de Francia?",
        "¿Cuánto es 5 x 5?",
        "¿Cuál es el lenguaje principal de Android?"
    )

    private val respuestas = arrayOf("Paris", "25", "Kotlin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val name = intent.getStringExtra("name") ?: ""
        val interests = intent.getStringExtra("interests") ?: ""

        val randomIndex = Random.nextInt(preguntas.size)
        val pregunta = preguntas[randomIndex]
        val respuestaCorrecta = respuestas[randomIndex]

        setContent {
            JuegoScreen(
                pregunta = pregunta,
                onValidar = { respuesta ->
                    val correcto = respuesta.equals(respuestaCorrecta, ignoreCase = true)
                    val intent = Intent(this, ResumenActivity::class.java).apply {
                        putExtra("name", name)
                        putExtra("interests", interests)
                        putExtra("respuesta", if (correcto) "Correcta" else "Incorrecta")
                    }
                    startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun JuegoScreen(pregunta: String, onValidar: (String) -> Unit) {
    var respuesta by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Pregunta sorpresa:", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = pregunta)
        OutlinedTextField(
            value = respuesta,
            onValueChange = { respuesta = it },
            label = { Text("Tu respuesta") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onValidar(respuesta) }) {
            Text("Validar y Continuar")
        }
    }
}
