class MutableStack<T> {
    private var elems: List<T> = []

    fun push(elem: T) { elems = elems + elem }
    fun pop(): T {
        val elem = top
        elems = elems.dropLast(1)
        return elem
    }
    val top: T get() = elems.last()
    fun isEmpty(): Boolean = elems.isEmpty()
}