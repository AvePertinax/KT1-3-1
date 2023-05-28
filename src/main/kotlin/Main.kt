import java.time.LocalDateTime
import java.time.ZoneId

fun minuteEnding(minutes: Long): String {
    return when {
        minutes % 10L == 1L && minutes % 100L != 11L -> "минуту"
        minutes % 10L in 2L..4L && minutes % 100L !in 12L..14L -> "минуты"
        else -> "минут"
    }
}

fun hourEnding(hours: Long): String {
    return when {
        hours % 10L == 1L && hours % 100L != 11L -> "час"
        hours % 10L in 2L..4L && hours % 100L !in 12L..14L -> "часа"
        else -> "часов"
    }
}

fun dayEnding(days: Long): String {
    return when {
        days % 10L == 1L && days % 100L != 11L -> "день"
        days % 10L in 2L..4L && days % 100L !in 12L..14L -> "дня"
        else -> "дней"
    }
}

fun agoToText(secondsAgo: Long): String {
    val minutesAgo: Long = secondsAgo / 60L
    val hoursAgo: Long = minutesAgo / 60L
    val daysAgo: Long = hoursAgo / 24L

    val minutesText = when {
        minutesAgo == 0L -> "только что"
        minutesAgo < 60L -> "$minutesAgo ${minuteEnding(minutesAgo)} назад"
        else -> "${hoursAgo} ${hourEnding(hoursAgo)} назад"
    }

    val daysText = when {
        daysAgo == 0L -> ""
        daysAgo == 1L -> "вчера"
        daysAgo == 2L -> "позавчера"
        else -> "$daysAgo ${dayEnding(daysAgo)} назад"
    }

    return "$minutesText $daysText"
}

fun main() {
    // задаем начальное время, которое мы хотим использовать в программе
    val start = LocalDateTime.of(2023, 5, 28, 17, 25, 22)

    // получаем текущее время в секундах
    val now = System.currentTimeMillis() / 1000L

    // вычисляем количество секунд, прошедших с начального времени до текущего времени
    val startSeconds = start.atZone(ZoneId.systemDefault()).toEpochSecond()
    val secondsAgo: Long = now - startSeconds

    println("Был(а) в сети ${agoToText(secondsAgo)}")

}
