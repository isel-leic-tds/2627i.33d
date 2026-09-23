
class Date(val year: Int, val month: Int = 1, val day: Int = 1) {
    init {
        require(year in 1582..2200)
        require(month in 1..12) { "Month must be between 1 and 12" }
        require(day in 1..lastDayOfMonth) { "Day must be between 1 and $lastDayOfMonth" }
    }
}

val Int.isLeapYear: Boolean
    get() = this % 4 == 0 && this % 100 != 0 || this % 400 == 0

val Date.hasLeapYear get() = year.isLeapYear

private val daysOfMonths = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

val Date.lastDayOfMonth get() =
    if (month == 2 && year.isLeapYear) 29
    else daysOfMonths[month - 1]


