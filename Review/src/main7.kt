// Set collection types
fun main() {
    val s: Set<Int> = [2,7,5,5]
    println(s)

    var s1 = s
    s1 = s1 + 10
    println(7 in s)
    println(s1)
}