fun main() {
    val sistema = SistemaGestionEstudiantes()

    do {
        println("\n--- Menú Sistema de Gestión de Estudiantes ---")
        println("1. Agregar estudiante")
        println("2. Mostrar lista de estudiantes")
        println("3. Buscar estudiante por nombre")
        println("4. Ordenar estudiantes por promedio")
        println("5. Eliminar estudiante")
        println("6. Salir")
        print("Selecciona una opción: ")

        when (readLine()?.toIntOrNull()) {
            1 -> sistema.agregarEstudiante()
            2 -> sistema.mostrarEstudiantes()
            3 -> sistema.buscarEstudiante()
            4 -> sistema.ordenarPorPromedio()
            5 -> sistema.eliminarEstudiante()
            6 -> {
                println("Saliendo del sistema. ¡Hasta luego!")
                return
            }
            else -> println("Opción inválida, intenta de nuevo.")
        }
    } while (true)
}

class Estudiante(
    val nombre: String,
    val edad: Int,
    val calificaciones: List<Double>
) {
    val promedio: Double
        get() = calificaciones.average()

    val aprobado: Boolean
        get() = promedio >= 60.0

    override fun toString(): String {
        val estado = if (aprobado) "Aprobado" else "Reprobado"
        return "Nombre: $nombre, Edad: $edad, Promedio: %.2f, Estado: $estado".format(promedio)
    }
}

class SistemaGestionEstudiantes {
    private val estudiantes = mutableListOf<Estudiante>()

    fun agregarEstudiante() {
        try {
            print("Nombre del estudiante: ")
            val nombre = readLine()?.trim() ?: ""
            if (nombre.isEmpty()) {
                println("El nombre no puede estar vacío.")
                return
            }

            print("Edad del estudiante: ")
            val edad = readLine()?.toIntOrNull()
            if (edad == null || edad <= 0) {
                println("Edad inválida.")
                return
            }

            val calificaciones = mutableListOf<Double>()
            for (i in 1..3) {  // Puedes cambiar la cantidad de calificaciones si quieres
                print("Calificación $i (0-100): ")
                val calif = readLine()?.toDoubleOrNull()
                if (calif == null || calif !in 0.0..100.0) {
                    println("Calificación inválida.")
                    return
                }
                calificaciones.add(calif)
            }

            val estudiante = Estudiante(nombre, edad, calificaciones)
            estudiantes.add(estudiante)
            println("Estudiante agregado correctamente.")

        } catch (e: Exception) {
            println("Error al agregar estudiante: ${e.message}")
        }
    }

    fun mostrarEstudiantes() {
        if (estudiantes.isEmpty()) {
            println("No hay estudiantes registrados.")
            return
        }
        println("\n--- Lista de estudiantes ---")
        estudiantes.forEachIndexed { index, estudiante ->
            println("${index + 1}. $estudiante")
        }
    }

    fun buscarEstudiante() {
        print("Ingresa el nombre para buscar: ")
        val busqueda = readLine()?.trim()?.lowercase() ?: ""
        val resultados = estudiantes.filter { it.nombre.lowercase().contains(busqueda) }
        if (resultados.isEmpty()) {
            println("No se encontró ningún estudiante con ese nombre.")
        } else {
            println("Estudiantes encontrados:")
            resultados.forEach { println(it) }
        }
    }

    fun ordenarPorPromedio() {
        if (estudiantes.isEmpty()) {
            println("No hay estudiantes para ordenar.")
            return
        }
        estudiantes.sortByDescending { it.promedio }
        println("Estudiantes ordenados por promedio (de mayor a menor).")
        mostrarEstudiantes()
    }

    fun eliminarEstudiante() {
        print("Ingresa el nombre del estudiante a eliminar: ")
        val nombreEliminar = readLine()?.trim()?.lowercase() ?: ""
        val estudiante = estudiantes.find { it.nombre.lowercase() == nombreEliminar }
        if (estudiante != null) {
            estudiantes.remove(estudiante)
            println("Estudiante eliminado correctamente.")
        } else {
            println("Estudiante no encontrado.")
        }
    }
}
