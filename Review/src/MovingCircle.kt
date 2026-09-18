
data class Point(val x: Double, val y: Double) {
    //override fun equals(other: Any?): Boolean =
    //    other is Point && x == other.x && y == other.y
    //override fun hashCode(): Int =
    //    x.hashCode() * 31 + y.hashCode()
}

val origin = Point(0.0, 0.0)

class MovingCircle(radius: Double, at: Point = origin) : Circle(radius) {
    var center: Point = at
        private set
    fun move(dx: Double, dy: Double) {
        center = Point(center.x + dx, center.y + dy)
    }
}

fun main() {
    val c = MovingCircle(1.0)
    println("c = Circle(${c.radius}, ${c.center})")
    val p = Point(0.0,0.0)
    println(p == origin)
    val p1 = p.copy(y=27.5)
    //c.center = Point(1.0, 2.0)
    c.move(dx=1.0,dy=2.0)
    val c2: Circle = c
}