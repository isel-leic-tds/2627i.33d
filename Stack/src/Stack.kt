
interface Stack<T>: Iterable<T> {
    val top: T
    fun isEmpty(): Boolean
    fun push(e: T): Stack<T>
    fun pop(): Stack<T>
}

private object StackEmpty: Stack<Any> {
    private fun throwEmpty(): Nothing {
        throw NoSuchElementException("stack empty")
    }

    override val top get() = throwEmpty()
    override fun isEmpty() = true
    override fun pop() = throwEmpty()
    override fun push(e: Any): Stack<Any> = StackNotEmpty(e,this)
    override fun iterator() = object : Iterator<Nothing> {
        override fun next() = throwEmpty()
        override fun hasNext() = false
    }
}

@Suppress("UNCHECKED_CAST")
fun <T> Stack() = StackEmpty as Stack<T>

private class StackNotEmpty<T>(
    override val top: T,
    private val tail: Stack<T>
): Stack<T> {
    override fun isEmpty() = false
    override fun pop() = tail
    override fun push(e: T) = StackNotEmpty(e,this)
    override fun iterator() = object : Iterator<T> {
        var curr: Stack<T> = this@StackNotEmpty
        override fun hasNext() = !curr.isEmpty()
        override fun next() =
            ((curr as? StackNotEmpty) ?: throw NoSuchElementException("No more elements"))
                .also { curr = it.tail }.top
    }
    override fun equals(other: Any?) =
        other is StackNotEmpty<*> && top == other.top && tail == other.tail

    override fun hashCode(): Int =
        fold(0) { r, e -> r*31 + e.hashCode() }
}


//class Stack<T> private constructor(
//    private val head: Node<T>? = null
//): Iterable<T> {
//    private class Node<E>(val value: E, val next: Node<E>? = null)
//    constructor() : this(null)
//    private val first: Node<T> get() =
//        head ?: throw NoSuchElementException("Stack empty")
//
//    fun push(elem: T) = Stack(Node(elem, head))
//    fun isEmpty(): Boolean = head == null
//    val top: T get() = first.value
//    fun pop(): Stack<T> = Stack(first.next)
//    fun pop2(): Pair<Stack<T>, T> =
//        first.let{ Stack(it.next) to it.value }
//
//    fun forEach0(action: (T) -> Unit) { doAction(head, action) }
//    private tailrec fun doAction(n: Node<T>?, action: (T) -> Unit) {
//        if (n == null) return
//        action(n.value)
//        doAction(n.next, action)
//    }
//
//    override fun iterator() = object : Iterator<T> {
//        var node: Node<T>? = head
//        override fun hasNext(): Boolean = node != null
//        override fun next(): T =
//            node?.let {
//                node = it.next
//                it.value
//            } ?: throw NoSuchElementException("No more elements")
//    }
//
//    override fun equals(other: Any?): Boolean {
//        if (other !is Stack<*>) return false
//        val io = other.iterator()
//        for(e in this)
//            if (!io.hasNext() || e!=io.next())
//                return false
//        return !io.hasNext()
//    }
//
//    override fun hashCode() =
//        fold(0){ res,e -> res*31+e.hashCode() }
//}