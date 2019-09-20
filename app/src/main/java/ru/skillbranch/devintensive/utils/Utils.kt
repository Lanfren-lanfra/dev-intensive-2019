package ru.skillbranch.devintensive.utils

object Utils {
    /**
     * принимающий в качестве аргумента полное имя пользователя
     * и возвращающий пару значений "firstName lastName"
     */
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var names: List<String?>? = fullName?.split(" ")
        var fn = names?.getOrNull(0)
        var ln = names?.getOrNull(1)

        fn = emptyToNull(fn)
        ln = emptyToNull(ln)

        return Pair(fn, ln)

    }

    /**
     * возвращающий строку с первыми буквами имени и фамилии в верхнем регистре
     */
    fun toInitials(firstName: String?, lastName: String?): String? {
        var f = emptyToNull(firstName)?.substring(0, 1)?.toUpperCase()
        var l = emptyToNull(lastName)?.substring(0, 1)?.toUpperCase()
        return when {
            f == null && l == null -> null
            f == null -> l
            l == null -> f
            else -> "${f}${l}"
        }
    }

    fun transliteration(payload: String, divider: String = " "): String? {

        if (payload.isBlank()) return null

        var translated = ""

        for (pchar in payload) {
            translated += transliterateLitera(pchar)
        }

        return translated.replace(" ", divider)
    }

    private fun transliterateLitera(pchar: Char): String {
        var uppercaseFlag = pchar.isUpperCase()
        var ch = if (uppercaseFlag) pchar.toLowerCase() else pchar

        var translated = when (ch) {
            'а' -> "a"
            'б' -> "b"
            'в' -> "v"
            'г' -> "g"
            'д' -> "d"
            'е' -> "e"
            'ё' -> "e"
            'ж' -> "zh"
            'з' -> "z"
            'и' -> "i"
            'й' -> "i"
            'к' -> "k"
            'л' -> "l"
            'м' -> "m"
            'н' -> "n"
            'о' -> "o"
            'п' -> "p"
            'р' -> "r"
            'с' -> "s"
            'т' -> "t"
            'у' -> "u"
            'ф' -> "f"
            'х' -> "h"
            'ц' -> "c"
            'ч' -> "ch"
            'ш' -> "sh"
            'щ' -> "sh'"
            'ъ' -> ""
            'ы' -> "i"
            'ь' -> ""
            'э' -> "e"
            'ю' -> "yu"
            'я' -> "ya"
            else -> ch.toString()
        }

        return if (uppercaseFlag) translated.capitalize() else translated

    }




    private fun emptyToNull(str: String?): String? {
        when {
            str.equals(null) || str!!.isBlank() -> return null
            else -> return str
        }
    }
}