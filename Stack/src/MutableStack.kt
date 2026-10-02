class MutableStack<T> {
    private class Node<E>(val value: E, val next: Node<E>? = null)
    private var head: Node<T>? = null
    private val first: Node<T> get() =
        head ?: throw NoSuchElementException("Stack empty")

    fun push(elem: T) { head = Node(elem, head) }
    fun pop(): T = first.also { head = it.next }.value
    val top: T get() = first.value
    fun isEmpty(): Boolean = head == null

    override fun equals(other: Any?): Boolean =
        other is MutableStack<*> && equalNodes(head, other.head)

    private tailrec fun equalNodes(n1: Node<T>?, n2: Node<*>?): Boolean =
        when {
            n1 == null && n2 == null -> true
            n1 == null || n2 == null -> false
            n1.value != n2.value -> false
            else -> equalNodes(n1.next, n2.next)
        }

    override fun hashCode(): Int = computeHash(1, head)

    private tailrec fun computeHash(result: Int, n: Node<T>?): Int =
        if (n == null) result
        else computeHash(31 * result + n.value.hashCode(), n.next)
}