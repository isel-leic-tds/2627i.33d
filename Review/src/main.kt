// Input & Output
fun main() {
    print("nome: ")
    val name: String = readlnOrNull() ?: "?"
    println("Olá, $name!")
    println(readln())
}