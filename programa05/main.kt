fun main() {
    val estudiantes = mutableListOf<Estudiante>()
    var opcion: Int

    do {
        println("\n--- Menú de Registro de Estudiantes ---")
        println("1. Registrar estudiante")
        println("2. Ver lista de estudiantes")
        println("3. Salir")
        print("Elige una opción: ")

        val input = readLine()
        opcion = input?.toIntOrNull() ?: -1

        when (opcion) {
            1 -> registrarEstudiante(estudiantes)
            2 -> mostrarEstudiantes(estudiantes)
            3 -> println("¡Hasta luego!")
            else -> println("Opción inválida. Intenta de nuevo.")
        }
    } while (opcion != 3)
}

fun registrarEstudiante(estudiantes: MutableList<Estudiante>) {
    var nombre: String
    var edad: Int

    // Validar nombre no vacío
    do {
        print("Ingresa el nombre del estudiante: ")
        nombre = readLine()?.trim() ?: ""
        if (nombre.isEmpty()) {
            println("El nombre no puede estar vacío. Intenta de nuevo.")
        }
    } while (nombre.isEmpty())

    // Validar edad número entero positivo
    while (true) {
        print("Ingresa la edad del estudiante: ")
        val inputEdad = readLine()
        edad = inputEdad?.toIntOrNull() ?: -1
        if (edad > 0) {
            break
        } else {
            println("La edad debe ser un número entero positivo. Intenta de nuevo.")
        }
    }

    estudiantes.add(Estudiante(nombre, edad))
    println("Estudiante registrado correctamente.")
}

fun mostrarEstudiantes(estudiantes: List<Estudiante>) {
    if (estudiantes.isEmpty()) {
        println("No hay estudiantes registrados.")
    } else {
        println("\n--- Lista de Estudiantes ---")
        estudiantes.forEachIndexed { index, estudiante ->
            println("${index + 1}. Nombre: ${estudiante.nombre} | Edad: ${estudiante.edad}")
        }
    }
}

data class Estudiante(val nombre: String, val edad: Int)