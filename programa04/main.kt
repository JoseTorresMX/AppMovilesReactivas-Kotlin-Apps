import kotlin.random.Random

fun main() {
    println("🎲 Bienvenido al juego Adivina el Número")
    val numeroSecreto = Random.nextInt(1, 101) // Número entre 1 y 100
    var intentos = 0

    while (true) {
        print("Ingresa tu número: ")
        val input = readLine()

        val numeroIngresado = input?.toIntOrNull()

        if (numeroIngresado == null || numeroIngresado !in 1..100) {
            println("⚠️ Ingresa un número válido entre 1 y 100.")
            continue
        }

        intentos++

        when {
            numeroIngresado < numeroSecreto -> println("🔻 Demasiado bajo. Intenta de nuevo.")
            numeroIngresado > numeroSecreto -> println("🔺 Demasiado alto. Intenta de nuevo.")
            else -> {
                println("🎉 ¡Felicidades! Adivinaste el número $numeroSecreto en $intentos intentos.")
                break
            }
        }
    }
}