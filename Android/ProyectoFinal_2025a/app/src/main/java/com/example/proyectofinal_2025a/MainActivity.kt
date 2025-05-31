package com.example.proyectofinal_2025a

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(onNext = { name, interests ->
                val intent = Intent(this, JuegoActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("interests", interests.joinToString(", "))
                }
                startActivity(intent)
            })
        }
    }
}

@Composable
fun MainScreen(onNext: (String, List<String>) -> Unit) {
    var name by remember { mutableStateOf("") }
    var deportes by remember { mutableStateOf(false) }
    var musica by remember { mutableStateOf(false) }
    var hobbies by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Bienvenido", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Selecciona tus intereses:")
        Row {
            Checkbox(checked = deportes, onCheckedChange = { deportes = it })
            Text("Deportes", modifier = Modifier.padding(start = 8.dp))
        }
        Row {
            Checkbox(checked = musica, onCheckedChange = { musica = it })
            Text("Música", modifier = Modifier.padding(start = 8.dp))
        }
        Row {
            Checkbox(checked = hobbies, onCheckedChange = { hobbies = it })
            Text("Hobbies", modifier = Modifier.padding(start = 8.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val selected = mutableListOf<String>()
            if (deportes) selected.add("Deportes")
            if (musica) selected.add("Música")
            if (hobbies) selected.add("Hobbies")
            onNext(name, selected)
        }) {
            Text("Siguiente")
        }
    }
}
