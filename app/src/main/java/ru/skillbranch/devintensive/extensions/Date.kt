package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60
const val DAY = HOUR * 24

fun Date.format(pattern: String? = "HH:mm:ss dd.MM.yy"): String {
    return SimpleDateFormat(pattern, Locale("ru")).format(this)
}

fun Date.add(value: Int = 0, unit: TimeUnits = TimeUnits.SECOND): Date {

    var time = this.time

    time += when (unit) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time
    return this
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value:Int): String{

        var declensions = when (this){
            SECOND -> listOf("секунду", "секунды", "секунд")
            MINUTE -> listOf("минуту", "минуты", "минут")
            HOUR -> listOf("час", "часа", "часов")
            DAY -> listOf("день", "дня", "дней")
        }

        var wordInDeclension = when (value % 100){
            in (11..14) -> declensions[2]
            else -> when (value % 10){
                1 -> declensions[0]
                in (2..4) -> declensions[1]
                else -> declensions[2]
            }
        }

        return "${value} ${wordInDeclension}"
    }
}




fun Date.humanizeDiff(date:Date = Date()): String {
    var diff = (date.time - this.time)

    var humanized = when (diff / SECOND) {
        in (0..1) -> "только что"
        in (2..45) -> "несколько секунд назад"
        in (-45..-2) -> "через секунду"
        in (46..75) -> "минуту назад"
        in (-75..-46) -> "через минуту"
        else -> {
            when (val diffMinutes = (diff / MINUTE).toInt()) {
                in (1..45) -> "${TimeUnits.MINUTE.plural(diffMinutes)} назад"
                in (-45..-1) -> "через ${TimeUnits.MINUTE.plural(Math.abs(diffMinutes))}"
                in (46..75) -> "час назад"
                in (-75..-46) -> "через час"
                else -> {
                    when (val diffHours = (diff / HOUR).toInt()) {
                        in (1..22) -> "${TimeUnits.HOUR.plural(diffHours)} назад"
                        in (-22..-1) -> "через ${TimeUnits.HOUR.plural(Math.abs(diffHours))}"
                        in (23..26) -> "день назад"
                        in (-26..-23) -> "через день"
                        else -> {
                            when (val diffDays = (diff / DAY).toInt()) {
                                in (1..360) -> "${TimeUnits.DAY.plural( diffDays)} назад"
                                in (-360..-1) -> "через ${TimeUnits.DAY.plural(Math.abs(diffDays))}"
                                else -> {
                                    when {
                                        diffDays > 360 -> "более года назад"
                                        else -> "более чем через год"
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    return humanized
}