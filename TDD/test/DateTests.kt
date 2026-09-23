import kotlin.test.*

class DateTests {
    @Test fun createDate() {
        val sut = Date(2026,9,23)
        assertEquals(2026, sut.year) { "Wrong year ${sut.year}" }
        assertEquals(9, sut.month, "Wrong month")
        assertEquals(23, sut.day)
    }
}