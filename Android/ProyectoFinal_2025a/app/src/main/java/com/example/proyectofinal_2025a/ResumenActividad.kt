package com.example.proyectofinal_2025a

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class ResumenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val name = intent.getStringExtra("name") ?: ""
        val interests = intent.getStringExtra("interests") ?: ""
        val respuesta = intent.getStringExtra("respuesta") ?: ""

        val permisos = listOf(
            Manifest.permission.READ_PHONE_STATE
        )

        setContent {
            ResumenScreen(
                name = name,
                interests = interests,
                respuesta = respuesta,
                permisos = permisos,
                getDeviceInfo = { obtenerInfoDispositivo() },
                onAbrirConfig = { abrirConfiguracion() }
            )
        }
    }

    private fun obtenerInfoDispositivo(): String {
        val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
        return "Operador: ${telephonyManager.networkOperatorName}\n" +
                "Estado SIM: ${telephonyManager.simState}"
    }

    private fun abrirConfiguracion() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", packageName, null)
        }
        startActivity(intent)
    }
}

@Composable
fun ResumenScreen(
    name: String,
    interests: String,
    respuesta: String,
    permisos: List<String>,
    getDeviceInfo: () -> String,
    onAbrirConfig: () -> Unit
) {
    val infoDispositivo = getDeviceInfo()
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Resumen", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Nombre: $name")
        Text("Intereses: $interests")
        Text("Respuesta Trivia: $respuesta")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Información del dispositivo:")
        Text(infoDispositivo)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Permisos activos:")
        permisos.forEach { permiso ->
            Text(permiso)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Advertencia: Esta app accede a información sensible. Ten cuidado.")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onAbrirConfig) {
            Text("Abrir Configuración de la App")
        }
    }
}
