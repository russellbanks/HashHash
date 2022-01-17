import java.util.concurrent.TimeUnit

object Time {

    fun formatElapsedTime(nanoSeconds: Long): String {
        val microseconds = TimeUnit.MICROSECONDS.convert(nanoSeconds, TimeUnit.NANOSECONDS)
        val milliseconds = TimeUnit.MILLISECONDS.convert(nanoSeconds, TimeUnit.NANOSECONDS)
        val seconds = TimeUnit.SECONDS.convert(nanoSeconds, TimeUnit.NANOSECONDS)
        val minutes = TimeUnit.MINUTES.convert(nanoSeconds, TimeUnit.NANOSECONDS)
        val hours = TimeUnit.HOURS.convert(nanoSeconds, TimeUnit.NANOSECONDS)
        val days = TimeUnit.DAYS.convert(nanoSeconds, TimeUnit.NANOSECONDS)

        return when {
            microseconds in 1..999 -> "$microseconds ${getMicrosecondsFormat(microseconds)}"
            milliseconds in 1..999 -> "$milliseconds ${getMillisecondsFormat(milliseconds)}, ${microseconds % 1000} ${getMicrosecondsFormat(microseconds % 1000)}"
            seconds in 1..59 -> "$seconds ${getSecondsFormat(seconds)}, ${milliseconds % 1000} ${getMillisecondsFormat(milliseconds % 1000)}"
            minutes in 1..59 -> "$minutes ${getMinutesFormat(minutes)}, ${seconds % 60} ${getSecondsFormat(seconds % 60)}"
            hours in 1..23 -> "$hours ${getHoursFormat(hours)}, ${minutes % 60} ${getMinutesFormat(minutes % 60)}, ${seconds % 3600}, ${getSecondsFormat(seconds % 3600)}"
            days in 1..6 -> "$days ${getDaysFormat(days)}, $hours, ${getHoursFormat(hours % 24)}, $minutes ${getMinutesFormat(minutes % 1440)}"
            else -> "$nanoSeconds ${getNanosecondsFormat(nanoSeconds)}"
        }
    }

    private fun getNanosecondsFormat(nanoSeconds: Long) = if (nanoSeconds == 1L) "nanosecond" else "nanoseconds"

    private fun getMicrosecondsFormat(microseconds: Long) = if (microseconds == 1L) "microsecond" else "microseconds"

    private fun getMillisecondsFormat(milliseconds: Long) = if (milliseconds == 1L) "millisecond" else "milliseconds"

    private fun getSecondsFormat(seconds: Long) = if (seconds == 1L) "second" else "seconds"

    private fun getMinutesFormat(minutes: Long) = if (minutes == 1L) "minute" else "minutes"

    private fun getHoursFormat(hours: Long) = if (hours == 1L) "hour" else "hours"

    private fun getDaysFormat(days: Long) = if (days == 1L) "day" else "days"
}