private const val GREGORIAN_START = 1582
private const val MAX_YEAR = 2200

class Date(val year: Int, val month: Int = 1, val day: Int = 1) {
    init {
        require(year in GREGORIAN_START..MAX_YEAR)
        require(month in 1..daysOfMonths.size) { "Month must be between 1 and 12" }
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

tailrec fun Date.addDays(days: Int): Date {
    require(days >= 0) { "Days must be positive" }
    return when {
        days + day <= lastDayOfMonth ->
            Date(year, month, day + days)
        month < 12 ->
            Date(year, month + 1).addDays(days - (lastDayOfMonth - day + 1))
        else ->
            Date(year + 1).addDays(days - (lastDayOfMonth - day + 1))
    }
}

operator fun Date.plus(days: Int): Date = addDays(days)
operator fun Int.plus(dt: Date): Date = dt.addDays(this)

