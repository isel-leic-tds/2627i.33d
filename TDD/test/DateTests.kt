import kotlin.test.*

class DateTests {
    @Test fun `Make Illegal Dates Unrepresentable`() {
        val ex = assertFailsWith<IllegalArgumentException> { Date(2024, 2, 30) }
        assertEquals("Day must be between 1 and 29", ex.message)
        assertFailsWith<IllegalArgumentException> { Date(2024, 4, 31) }
        assertFailsWith<IllegalArgumentException> { Date(2024, 13, 1) }
        assertFailsWith<IllegalArgumentException> { Date(1400, 1, 1) }
    }
    @Test fun `Get the last day of month`() {
        val sut = Date(2024,2)
        assertEquals(29, sut.lastDayOfMonth)
        val sut2 = Date(2025,2)
        assertEquals(28, sut2.lastDayOfMonth)
        val sut3 = Date(2024,4)
        assertEquals(30, sut3.lastDayOfMonth)
    }
    @Test fun `Check if has a leap year`() {
        val sut = Date(2024)
        assertTrue(sut.hasLeapYear)
        val sut2 = Date(2025)
        assertFalse(sut2.hasLeapYear)
    }
    @Test fun createDate() {
        val sut = Date(2026,9,23)
        assertEquals(2026, sut.year) { "Wrong year ${sut.year}" }
        assertEquals(9, sut.month, "Wrong month")
        assertEquals(23, sut.day)
    }
    @Test fun `Create Date without day`() {
        val sut = Date(2026,9)
        assertEquals(2026, sut.year)
        assertEquals(9, sut.month)
        assertEquals(1, sut.day)
    }
    @Test fun `Create Date with only the year`() {
        val sut = Date(2026)
        assertEquals(2026, sut.year)
        assertEquals(1, sut.month)
        assertEquals(1, sut.day)
    }
}