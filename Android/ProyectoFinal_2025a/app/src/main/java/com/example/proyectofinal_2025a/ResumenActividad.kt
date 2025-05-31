package com.example.proyectofinal_2025a

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ResumenActividad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resumen)

        val textViewResumen = findViewById<TextView>(R.id.textViewResumen)
        val botonPermisos = findViewById<Button>(R.id.botonPermisos)

        val nombre = intent.getStringExtra("nombre")
        val preferencias = intent.getStringArrayExtra("preferencias")?.joinToString(", ")
        val pregunta = intent.getStringExtra("pregunta")
        val respuesta = intent.getStringExtra("respuesta")

        val fabricante = Build.MANUFACTURER
        val modelo = Build.MODEL
        val version = Build.VERSION.RELEASE

        val datos = "Perfil de: $nombre\nPreferencias: $preferencias\n" +
                "Pregunta sorpresa: $pregunta\nRespuesta correcta: $respuesta\n" +
                "\nDatos del dispositivo:\nFabricante: $fabricante\nModelo: $modelo\nVersión: $version"

        textViewResumen.text = datos

        val permisos = StringBuilder()
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            permisos.append("READ_PHONE_STATE: PERMITIDO\n")
        } else {
            permisos.append("READ_PHONE_STATE: DENEGADO\n")
        }

        textViewResumen.append("\nPermisos:\n$permisos")

        botonPermisos.setOnClickListener {
            val intentConfig = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", packageName, null)
            intentConfig.data = uri
            startActivity(intentConfig)
        }
    }
}
