// Ranges
fun main() {
    val r = 3..10
    val r1 = 'a'..'z'
    val r2 = 2.4 .. 7.5
    val r3 = false..true
    val r4 = "abc".."Xyz"
    println(r.count())
    for(i in r) {
        println(i)
    }
    println(3.2 in r2)
    println(r.first)
    println(r2.start)
    val r5 = 2 .. 27 step 2
    print(r5.last)
    val r6 = 2 ..< 8
    println(r6 == 2..7)
    val r7 = 10 downTo 0 step 3
    println(r7.last)
    for(x in 1..5) {
        println(x)
    }
    println(r.random())
}