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
        //val (sut3, top) = sut2.pop2()
        //assertEquals('B', top)
        //assertEquals('A', sut3.top)
    }
    @Test fun `Sum of elements in a stack using forEach`() {
        val sut = Stack<Int>().push(1).push(2).push(3)
        var sum = 0
        sut.forEach{ sum += it }
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
    @Test fun `verify equality`(){
        val s1 = Stack<Char>().push('A').push('B')
        val s2 = Stack<Char>().push('A').push('B')
        assertEquals(s1,s2)
        assertEquals(s1.hashCode(),s2.hashCode())
        assertEquals(s1,s1)
        assertNotEquals(s1, s2.pop())
        assertNotEquals(s1.pop(), s2)
    }
    @Test fun `Create stacks with elements`() {
        val sut1 = stackOf('A','B','C')
        val sut2 = Stack<Char>().push('A').push('B').push('C')
        assertEquals(sut2,sut1)
        val sut3 = Stack(3){ 'A'+ it }
        assertEquals(sut1,sut3)
    }
}