fun main() {
    val calificaciones = IntArray(10)
    
    // Ingresar calificaciones con validación
    for (i in calificaciones.indices) {
        while (true) {
            print("Ingresa la calificación ${i + 1} (0-100): ")
            val input = readLine()
            val calif = input?.toIntOrNull()
            if (calif != null && calif in 0..100) {
                calificaciones[i] = calif
                break
            } else {
                println("Calificación inválida. Debe ser un número entre 0 y 100.")
            }
        }
    }
    
    // Calcular promedio
    val promedio = calificaciones.average()
    // Calificación más alta
    val maxCalif = calificaciones.maxOrNull() ?: 0
    // Calificación más baja
    val minCalif = calificaciones.minOrNull() ?: 0
    
    // Contar calificaciones mayores y menores al promedio
    val mayores = calificaciones.count { it > promedio }
    val menores = calificaciones.count { it < promedio }
    
    // Mostrar resultados
    println("\n--- Resultados de la encuesta ---")
    println("Calificaciones: ${calificaciones.joinToString(", ")}")
    println("Promedio: %.2f".format(promedio))
    println("Calificación más alta: $maxCalif")
    println("Calificación más baja: $minCalif")
    println("Cantidad de calificaciones mayores al promedio: $mayores")
    println("Cantidad de calificaciones menores al promedio: $menores")
}
