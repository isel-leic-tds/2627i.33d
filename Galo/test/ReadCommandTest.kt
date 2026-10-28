import console.readCommand
import kotlin.test.*

class ReadCommandTest {
    @Test
    fun normalRead() {
        val (name,args) = readCommand { "ls -l" }
        assertEquals("LS",name)
        assertEquals(["-L"],args)
    }
    @Test
    fun firstEmptyLineRead() {
        val lines = ["", "  ", "ls -l" ]
        var i = 0
        val (name,args) = readCommand { lines[i++] }
        assertEquals("LS",name)
        assertEquals(["-L"],args)
    }

}