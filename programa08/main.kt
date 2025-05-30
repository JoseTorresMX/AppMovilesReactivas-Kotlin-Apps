fun main() {
    val inventario = mutableListOf<Producto>()
    var opcion: Int

    do {
        println("\n--- Menú de Gestión de Productos ---")
        println("1. Agregar producto")
        println("2. Mostrar productos")
        println("3. Buscar producto por nombre")
        println("4. Eliminar producto")
        println("5. Ordenar productos por precio")
        println("6. Ordenar productos por cantidad")
        println("7. Salir")
        print("Elige una opción: ")

        opcion = readLine()?.toIntOrNull() ?: -1

        when (opcion) {
            1 -> agregarProducto(inventario)
            2 -> mostrarProductos(inventario)
            3 -> buscarProducto(inventario)
            4 -> eliminarProducto(inventario)
            5 -> {
                inventario.sortBy { it.precio }
                println("Inventario ordenado por precio.")
            }
            6 -> {
                inventario.sortBy { it.cantidad }
                println("Inventario ordenado por cantidad.")
            }
            7 -> println("Saliendo del sistema...")
            else -> println("Opción inválida. Intenta de nuevo.")
        }
    } while (opcion != 7)
}

data class Producto(
    val nombre: String,
    var precio: Double,
    var cantidad: Int
)

fun agregarProducto(inventario: MutableList<Producto>) {
    print("Nombre del producto: ")
    val nombre = readLine()?.trim() ?: ""
    if (nombre.isEmpty()) {
        println("El nombre no puede estar vacío.")
        return
    }

    print("Precio del producto: ")
    val precio = readLine()?.toDoubleOrNull()
    if (precio == null || precio < 0) {
        println("Precio inválido.")
        return
    }

    print("Cantidad del producto: ")
    val cantidad = readLine()?.toIntOrNull()
    if (cantidad == null || cantidad < 0) {
        println("Cantidad inválida.")
        return
    }

    inventario.add(Producto(nombre, precio, cantidad))
    println("Producto agregado correctamente.")
}

fun mostrarProductos(inventario: List<Producto>) {
    if (inventario.isEmpty()) {
        println("No hay productos en el inventario.")
    } else {
        println("\n--- Lista de productos ---")
        inventario.forEachIndexed { index, producto ->
            println("${index + 1}. Nombre: ${producto.nombre}, Precio: ${producto.precio}, Cantidad: ${producto.cantidad}")
        }
    }
}

fun buscarProducto(inventario: List<Producto>) {
    print("Ingresa el nombre del producto a buscar: ")
    val busqueda = readLine()?.trim()?.lowercase() ?: ""

    val resultados = inventario.filter { it.nombre.lowercase().contains(busqueda) }

    if (resultados.isEmpty()) {
        println("No se encontró ningún producto con ese nombre.")
    } else {
        println("Productos encontrados:")
        resultados.forEach {
            println("Nombre: ${it.nombre}, Precio: ${it.precio}, Cantidad: ${it.cantidad}")
        }
    }
}

fun eliminarProducto(inventario: MutableList<Producto>) {
    print("Ingresa el nombre del producto a eliminar: ")
    val nombreEliminar = readLine()?.trim()?.lowercase() ?: ""

    val producto = inventario.find { it.nombre.lowercase() == nombreEliminar }

    if (producto != null) {
        inventario.remove(producto)
        println("Producto eliminado correctamente.")
    } else {
        println("Producto no encontrado.")
    }
}
