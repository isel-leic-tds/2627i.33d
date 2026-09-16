// Map collection types
fun main() {
    val ages = mapOf(
        "Alice" to 30u,
        "Bob" to 25u,
        Pair("Charlie",35u)
    )
    println(ages)
    val names = ages.keys
    val numbers = ages.values
    val pairs = ages.entries
    val pairs2 = ages.toList()
    println(ages["Luis"] ?: "Luis not found")
    //println(ages.get("Luis"))
}