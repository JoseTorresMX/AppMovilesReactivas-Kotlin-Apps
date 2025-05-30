fun main() {
    println("Bienvenido a la Calculadora Básica en Kotlin")

    // Pedir al usuario los dos números
    print("Ingrese el primer número: ")
    val numero1 = readLine()!!.toDouble()

    print("Ingrese el segundo número: ")
    val numero2 = readLine()!!.toDouble()

    // Pedir la operación
    println("Elija la operación a realizar:")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    print("Ingrese el número de la operación: ")
    val opcion = readLine()!!.toInt()

    // Llamar a la función correspondiente y mostrar el resultado
    val resultado = when (opcion) {
        1 -> suma(numero1, numero2)
        2 -> resta(numero1, numero2)
        3 -> multiplicacion(numero1, numero2)
        4 -> division(numero1, numero2)
        else -> {
            println("Opción no válida")
            null
        }
    }

    // Mostrar el resultado si es válido
    if (resultado != null) {
        println("El resultado es: $resultado")
    }
}

// Funciones para cada operación
fun suma(a: Double, b: Double): Double {
    return a + b
}

fun resta(a: Double, b: Double): Double {
    return a - b
}

fun multiplicacion(a: Double, b: Double): Double {
    return a * b
}

fun division(a: Double, b: Double): Double {
    return if (b != 0.0) {
        a / b
    } else {
        println("Error: No se puede dividir entre cero")
        0.0
    }
}
