import kotlin.test.*

class StackTest {
    @Test fun `Create an empty stack`() {
        val sut = Stack<Int>()
        assertTrue(sut.isEmpty())
        assertFailsWith<NoSuchElementException> { sut.top }
        assertFailsWith<NoSuchElementException> { sut.pop() }
    }
    @Test fun `Use a stack with elements`() {
        val sut = Stack<Char>().push('A').push('B').push('C')
        assertFalse(sut.isEmpty())
        assertEquals('C', sut.top)
        val sut2 = sut.pop()
        assertEquals('B', sut2.top)
        val (sut3, top) = sut2.pop2()
        assertEquals('B', top)
        assertEquals('A', sut3.top)
    }
    @Test fun `Sum of elements in a stack using forEach`() {
        val sut = Stack<Int>().push(1).push(2).push(3)
        var sum = 0
        sut.forEach0{ sum += it }
        assertEquals(6, sum)
    }
    @Test fun `Sum of elements in a stack using for`() {
        val sut = Stack<Int>().push(1).push(2).push(3)
        var sum = 0
        for(e in sut) sum += e
        assertEquals(6, sum)

        val iterator = sut.iterator()
        while(iterator.hasNext()) {
           val a = iterator.next()
           sum += a
        }
        assertEquals(12, sum)
    }
}