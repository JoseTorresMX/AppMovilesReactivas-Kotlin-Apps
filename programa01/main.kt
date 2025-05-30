fun main() {
    // Definimos dos variables inmutables (val) de tipo Int
    val num1: Int = 5
    val num2: Int = 10

    // Definimos una variable mutable (var) que almacena la suma de num1 y num2
    var resultado: Int = num1 + num2
    println("La suma es: $resultado")

    // Ahora almacenamos en la misma variable el producto de num1 y num2
    resultado = num1 * num2
    println("El producto es: $resultado")
}