import java.util.Scanner

data class Estudiante(var nombre: String, var edad: Int)

val estudiantes = mutableListOf<Estudiante>()
val scanner = Scanner(System.`in`)

fun main() {
    var opcion: Int

    do {
        mostrarMenu()
        opcion = leerEntero("Seleccione una opción:")

        when (opcion) {
            1 -> mostrarEstudiantes()
            2 -> agregarEstudiante()
            3 -> eliminarEstudiante()
            4 -> editarEstudiante()
            5 -> println("\n👋 ¡Gracias por usar el programa! ¡Hasta luego!")
            else -> println("⚠️ Opción no válida. Intente de nuevo.")
        }

    } while (opcion != 5)
}

fun mostrarMenu() {
    println(
        """
        |----- MENÚ -----
        |1. Ver estudiantes
        |2. Agregar estudiante
        |3. Eliminar estudiante
        |4. Editar estudiante
        |5. Salir
    """.trimMargin()
    )
}

fun mostrarEstudiantes() {
    if (estudiantes.isEmpty()) {
        println("\nNo hay estudiantes registrados.")
    } else {
        println("\nLista de Estudiantes:")
        estudiantes.forEachIndexed { index, estudiante ->
            println("${index + 1}. Nombre: ${estudiante.nombre}, Edad: ${estudiante.edad} años")
        }
    }
}

fun agregarEstudiante() {
    println("\nIngrese el nombre del estudiante:")
    var nombre = scanner.nextLine().trim()

    while (nombre.isEmpty()) {
        println("⚠️ El nombre no puede estar vacío.")
        nombre = scanner.nextLine().trim()
    }

    val edad = leerEdad("Ingrese la edad del estudiante:")

    estudiantes.add(Estudiante(nombre, edad))
    println("✅ Estudiante agregado correctamente.")
}

fun eliminarEstudiante() {
    if (estudiantes.isEmpty()) {
        println("\nNo hay estudiantes para eliminar.")
        return
    }

    mostrarEstudiantes()
    val numero = leerEntero("Ingrese el número de registro del estudiante a eliminar:")

    if (numero in 1..estudiantes.size) {
        val eliminado = estudiantes.removeAt(numero - 1)
        println("✅ Estudiante '${eliminado.nombre}' eliminado correctamente.")
    } else {
        println("⚠️ Número inválido.")
    }
}

fun editarEstudiante() {
    if (estudiantes.isEmpty()) {
        println("\nNo hay estudiantes para editar.")
        return
    }

    mostrarEstudiantes()
    val numero = leerEntero("Ingrese el número de registro del estudiante a editar:")

    if (numero in 1..estudiantes.size) {
        val estudiante = estudiantes[numero - 1]
        println("\nEditando a '${estudiante.nombre}' (Edad: ${estudiante.edad})")

        println("Ingrese el nuevo nombre (dejar vacío para no cambiar):")
        val nuevoNombre = scanner.nextLine().trim()
        if (nuevoNombre.isNotEmpty()) {
            estudiante.nombre = nuevoNombre
        }

        println("Ingrese la nueva edad (dejar vacío para no cambiar):")
        val nuevaEdadInput = scanner.nextLine().trim()
        if (nuevaEdadInput.isNotEmpty()) {
            val nuevaEdad = leerEdad("Ingrese la nueva edad:", nuevaEdadInput)
            estudiante.edad = nuevaEdad
        }

        println("✅ Estudiante actualizado correctamente.")
    } else {
        println("⚠️ Número inválido.")
    }
}

fun leerEdad(mensaje: String, input: String? = null): Int {
    var edadValida = input?.trim() ?: run {
        println(mensaje)
        scanner.nextLine().trim()
    }

    while (edadValida.toIntOrNull() == null || edadValida.toInt() <= 0) {
        println("⚠️ La edad debe ser un número entero positivo.")
        edadValida = scanner.nextLine().trim()
    }

    return edadValida.toInt()
}

fun leerEntero(mensaje: String): Int {
    println(mensaje)
    var input = scanner.nextLine().trim()
    while (input.toIntOrNull() == null) {
        println("⚠️ Debe ingresar un número válido.")
        input = scanner.nextLine().trim()
    }
    return input.toInt()
}