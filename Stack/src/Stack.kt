class Stack<T> private constructor(
    private val head: Node<T>? = null
): Iterable<T> {
    private class Node<E>(val value: E, val next: Node<E>? = null)
    constructor() : this(null)
    private val first: Node<T> get() =
        head ?: throw NoSuchElementException("Stack empty")

    fun push(elem: T) = Stack(Node(elem, head))
    fun isEmpty(): Boolean = head == null
    val top: T get() = first.value
    fun pop(): Stack<T> = Stack(first.next)
    fun pop2(): Pair<Stack<T>, T> =
        first.let{ Stack(it.next) to it.value }

    fun forEach0(action: (T) -> Unit) { doAction(head, action) }
    private tailrec fun doAction(n: Node<T>?, action: (T) -> Unit) {
        if (n == null) return
        action(n.value)
        doAction(n.next, action)
    }

    private inner class It : Iterator<T> {
        private var node: Node<T>? = head
        override fun hasNext(): Boolean = node != null
        override fun next(): T =
            node?.let {
                node = it.next
                it.value
            } ?: throw NoSuchElementException("No more elements")
    }

    override fun iterator(): Iterator<T> = It()
}