const val PI = 3.141592653589793

open class Circle(val radius: Double) {
    val area: Double
        get() = PI * radius * radius

    override fun toString(): String = "Circle($radius)"
}

fun Circle.getArea() = area

val String.size: Int get() = length

fun main() {
    val c = Circle(42.0)
    println(c.radius)
    println(c.area)
    println(c)
    println(c.getArea())
    println("ISEL".size)
}