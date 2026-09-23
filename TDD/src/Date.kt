class Date(val year: Int, val month: Int = 1, val day: Int = 1)

val Int.isLeapYear: Boolean
    get() = this % 4 == 0 && this % 100 != 0 || this % 400 == 0

val Date.hasLeapYear get() = year.isLeapYear
