fun main() {
    val x = readln().toBoolean() and readln().toBoolean()
    val y = true.and(false) // true and false
    //val x = readln().toBoolean() or readln().toBoolean()
    println(x)
}

infix fun Boolean.and2(other: Boolean): Boolean = this && other