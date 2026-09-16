// List collection types
fun main() {
    val l = [2,4,8,16]
    var l2 = listOf(2,4,8,18)
    println(l)
    println(l == l2)
    val l3 = ['i', 'o', 'u']
    for(c in [1,4,7]) {
        println(c)
    }
    println(l[2])
    val l4 = mutableListOf(1,2,3)
    l4.add(4)
    l2 = l2 + 4
    l2 = l2 - 8
    println(l2)
}