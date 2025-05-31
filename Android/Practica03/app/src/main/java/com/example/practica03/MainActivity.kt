package com.example.practica03

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Views
    private lateinit var txtPregunta: TextView
    private lateinit var chkOpcion1: CheckBox
    private lateinit var chkOpcion2: CheckBox
    private lateinit var chkOpcion3: CheckBox
    private lateinit var chkOpcion4: CheckBox
    private lateinit var btnVerificar: Button
    private lateinit var btnSiguiente: Button
    private lateinit var txtResultado: TextView
    private lateinit var txtPuntaje: TextView

    // Variables del juego
    private var puntaje = 0
    private var indicePregunta = 0

    // Datos del cuestionario
    private val preguntas = listOf(
        "¿Qué afirmaciones sobre Kotlin son correctas?",
        "¿Qué es cierto acerca de Android development?",
        "Conceptos básicos de Android"
    )

    private val opciones = listOf(
        listOf("Kotlin es un lenguaje moderno", "Kotlin solo funciona en iOS",
            "Android Studio soporta Kotlin", "Kotlin no funciona con Compose"),
        listOf("XML se usa para diseño de interfaces", "Jetpack Compose usa XML principalmente",
            "Un CheckBox permite selección múltiple", "Las Activities son componentes clave"),
        listOf("MainActivity hereda de AppCompatActivity", "findViewById() es obsoleto en nuevas versiones",
            "Kotlin es de tipado dinámico", "setContentView() carga el layout XML")
    )

    // Respuestas correctas (índices de las opciones correctas)
    private val respuestasCorrectas = listOf(
        listOf(0, 2),  // Primera pregunta: opciones 0 y 2 correctas
        listOf(0, 3),  // Segunda pregunta: opciones 0 y 3
        listOf(0, 3)   // Tercera pregunta: opciones 0 y 3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar vistas
        inicializarVistas()

        // Configurar listeners
        btnVerificar.setOnClickListener { verificarRespuestas() }
        btnSiguiente.setOnClickListener { siguientePregunta() }

        // Mostrar primera pregunta
        mostrarPreguntaActual()
    }

    private fun inicializarVistas() {
        txtPregunta = findViewById(R.id.txtPregunta)
        chkOpcion1 = findViewById(R.id.chkOpcion1)
        chkOpcion2 = findViewById(R.id.chkOpcion2)
        chkOpcion3 = findViewById(R.id.chkOpcion3)
        chkOpcion4 = findViewById(R.id.chkOpcion4)
        btnVerificar = findViewById(R.id.btnVerificar)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        txtResultado = findViewById(R.id.txtResultado)
        txtPuntaje = findViewById(R.id.txtPuntaje)
    }

    private fun mostrarPreguntaActual() {
        if (indicePregunta < preguntas.size) {
            txtPregunta.text = "Pregunta ${indicePregunta + 1}: ${preguntas[indicePregunta]}"

            chkOpcion1.text = opciones[indicePregunta][0]
            chkOpcion2.text = opciones[indicePregunta][1]
            chkOpcion3.text = opciones[indicePregunta][2]
            chkOpcion4.text = opciones[indicePregunta][3]

            // Reiniciar checkboxes
            chkOpcion1.isChecked = false
            chkOpcion2.isChecked = false
            chkOpcion3.isChecked = false
            chkOpcion4.isChecked = false

            // Reiniciar estado de botones
            txtResultado.text = ""
            btnVerificar.isEnabled = true
            btnSiguiente.isEnabled = false
            txtPuntaje.text = "Puntaje: $puntaje"
        }
    }

    private fun verificarRespuestas() {
        val respuestasUsuario = listOf(
            chkOpcion1.isChecked,
            chkOpcion2.isChecked,
            chkOpcion3.isChecked,
            chkOpcion4.isChecked
        )

        // Obtener índices de las respuestas seleccionadas
        val seleccionUsuario = respuestasUsuario.mapIndexed { index, selected ->
            if (selected) index else -1
        }.filter { it != -1 }

        val esCorrecto = seleccionUsuario == respuestasCorrectas[indicePregunta]

        if (esCorrecto) {
            txtResultado.text = "¡Correcto!"
            puntaje++
        } else {
            val respuestasCorrectasTexto = respuestasCorrectas[indicePregunta]
                .map { opciones[indicePregunta][it] }
                .joinToString(", ")
            txtResultado.text = "Incorrecto. Respuestas correctas: $respuestasCorrectasTexto"
        }

        txtPuntaje.text = "Puntaje: $puntaje"
        btnVerificar.isEnabled = false
        btnSiguiente.isEnabled = true
    }

    private fun siguientePregunta() {
        indicePregunta++

        if (indicePregunta < preguntas.size) {
            mostrarPreguntaActual()
        } else {
            finalizarCuestionario()
        }
    }

    private fun finalizarCuestionario() {
        txtPregunta.text = "Cuestionario completado!"

        // Ocultar elementos no necesarios
        listOf(chkOpcion1, chkOpcion2, chkOpcion3, chkOpcion4, btnVerificar).forEach {
            it.visibility = View.GONE
        }

        txtResultado.text = "Puntaje final: $puntaje de ${preguntas.size}"
        btnSiguiente.text = "Reiniciar"
        btnSiguiente.setOnClickListener { reiniciarCuestionario() }
    }

    private fun reiniciarCuestionario() {
        indicePregunta = 0
        puntaje = 0

        // Mostrar elementos nuevamente
        listOf(chkOpcion1, chkOpcion2, chkOpcion3, chkOpcion4, btnVerificar).forEach {
            it.visibility = View.VISIBLE
        }

        btnSiguiente.text = "Siguiente"
        btnSiguiente.setOnClickListener { siguientePregunta() }
        mostrarPreguntaActual()
    }
}