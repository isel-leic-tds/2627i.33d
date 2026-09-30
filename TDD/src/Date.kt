import kotlin.or
import kotlin.shl

private const val GREGORIAN_START = 1582
private const val MAX_YEAR = 2200

private const val DAY_BITS = 5
private const val MONTH_BITS = 4
private const val YEAR_BITS = 12

//@JvmInline to 2.4.10 version
value class Date private constructor(private val bits: Int) {
    constructor(year: Int, month: Int = 1, day: Int = 1)
            :this((year shl (DAY_BITS+MONTH_BITS)) or (month shl DAY_BITS) or day)
    init {
        require(year in GREGORIAN_START..MAX_YEAR)
        require(month in 1..daysOfMonths.size) { "Month must be between 1 and 12" }
        require(day in 1..lastDayOfMonth) { "Day must be between 1 and $lastDayOfMonth" }
    }

    val year get() = bits shr (DAY_BITS+MONTH_BITS)
    val month get() = (bits shr DAY_BITS) and ((1 shl MONTH_BITS) - 1)
    val day get() = bits and ((1 shl DAY_BITS) - 1)

    override fun toString() =
        "$year-" + "%02d-%02d".format(month, day)
    operator fun compareTo(other: Date): Int = bits - other.bits
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
