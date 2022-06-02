/**

HashHash
Copyright (C) 2022  Russell Banks

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

 */

package helper

import kotlin.time.Duration

object Time {

    fun formatElapsedTime(duration: Duration): String {
        val nanoseconds = duration.inWholeNanoseconds
        val microseconds = duration.inWholeMicroseconds
        val milliseconds = duration.inWholeMilliseconds
        val seconds = duration.inWholeSeconds
        val minutes = duration.inWholeMinutes
        val hours = duration.inWholeHours
        val days = duration.inWholeDays

        return when {
            microseconds in 1..999 -> "$microseconds ${getMicrosecondsFormat(microseconds)}"
            milliseconds in 1..999 -> "$milliseconds ${getMillisecondsFormat(milliseconds)}, ${microseconds % 1000} ${getMicrosecondsFormat(microseconds % 1000)}"
            seconds in 1..59 -> "$seconds ${getSecondsFormat(seconds)}, ${milliseconds % 1000} ${getMillisecondsFormat(milliseconds % 1000)}"
            minutes in 1..59 -> "$minutes ${getMinutesFormat(minutes)}, ${seconds % 60} ${getSecondsFormat(seconds % 60)}"
            hours in 1..23 -> "$hours ${getHoursFormat(hours)}, ${minutes % 60} ${getMinutesFormat(minutes % 60)}, ${seconds % 3600}, ${getSecondsFormat(seconds % 3600)}"
            days >= 1 -> "$days ${getDaysFormat(days)}, $hours, ${getHoursFormat(hours % 24)}, $minutes ${getMinutesFormat(minutes % 1440)}"
            else -> "$nanoseconds ${getNanosecondsFormat(nanoseconds)}"
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
