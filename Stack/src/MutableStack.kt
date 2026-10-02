class MutableStack<T> {
    private val elems = mutableListOf<T>()

    fun push(elem: T) { elems.addLast(elem) }
    fun pop(): T = top.also{ elems.removeLast() }
    val top: T get() = elems.last()
    fun isEmpty(): Boolean = elems.isEmpty()

    override fun equals(other: Any?): Boolean =
        other is MutableStack<*> && this.elems == other.elems

    override fun hashCode(): Int = elems.hashCode()
}