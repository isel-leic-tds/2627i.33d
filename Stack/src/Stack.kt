
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
    override fun push(e: Any) = StackNotEmpty(e,this)
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
            (curr as? StackNotEmpty ?: throw NoSuchElementException("No more elements"))
                .also { curr = it.tail }.top
    }
    override fun equals(other: Any?) =
        other is StackNotEmpty<*> && top == other.top && tail == other.tail
    override fun hashCode(): Int =
        fold(0) { r, e -> r*31 + e.hashCode() }
}

fun <T> stackOf(vararg elems: T) =
    elems.fold(Stack<T>()){ stk, e -> stk.push(e) }

fun <T> Stack(size: Int, init: (Int)->T ) =
    (0..<size).fold(Stack<T>()){ stk, idx -> stk.push( init(idx) )}