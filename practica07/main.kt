fun main() {
    val cuenta = CuentaBancaria(0.0)

    do {
        println("\n--- Menú de Gestión Bancaria ---")
        println("1. Depositar dinero")
        println("2. Retirar dinero")
        println("3. Mostrar saldo")
        println("4. Salir")
        print("Elige una opción: ")

        val opcion = readLine()?.toIntOrNull() ?: -1

        when (opcion) {
            1 -> {
                print("Ingresa la cantidad a depositar: ")
                val deposito = readLine()?.toDoubleOrNull()
                if (deposito != null && deposito > 0) {
                    cuenta.depositar(deposito)
                    println("Depósito realizado. Saldo actual: ${cuenta.saldo}")
                } else {
                    println("Cantidad inválida. Debe ser un número positivo.")
                }
            }
            2 -> {
                print("Ingresa la cantidad a retirar: ")
                val retiro = readLine()?.toDoubleOrNull()
                if (retiro != null && retiro > 0) {
                    val exito = cuenta.retirar(retiro)
                    if (exito) {
                        println("Retiro realizado. Saldo actual: ${cuenta.saldo}")
                    } else {
                        println("Saldo insuficiente para realizar el retiro.")
                    }
                } else {
                    println("Cantidad inválida. Debe ser un número positivo.")
                }
            }
            3 -> println("Saldo actual: ${cuenta.saldo}")
            4 -> println("Gracias por usar el sistema. ¡Hasta luego!")
            else -> println("Opción inválida. Intenta de nuevo.")
        }
    } while (opcion != 4)
}

class CuentaBancaria(var saldo: Double) {

    fun depositar(cantidad: Double) {
        saldo += cantidad
    }

    fun retirar(cantidad: Double): Boolean {
        return if (cantidad <= saldo) {
            saldo -= cantidad
            true
        } else {
            false
        }
    }
}
