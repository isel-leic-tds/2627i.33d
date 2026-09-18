class Thing : Any() {
    override fun equals(other: Any?): Boolean = other is Thing
    override fun toString(): String = "${this::class.simpleName}@${hashCode().toString(16)}"
    override fun hashCode(): Int = 42
}

fun main() {
    val t1 = Thing()
    val t2 = Thing()
    println(t1)
    println("t2 = $t2")
    println(t1 == t2)
    println(t1 === t2)
}