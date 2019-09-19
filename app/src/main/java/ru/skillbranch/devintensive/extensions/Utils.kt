package ru.skillbranch.devintensive.extensions

object Utils {
    /**
     * принимающий в качестве аргумента полное имя пользователя
     * и возвращающий пару значений "firstName lastName"
     */
    fun parseFullName(fullName: String?): Pair<String?, String?> {
        var names: List<String?>? = fullName?.split(" ")
        var fn = names?.getOrNull(0)
        var ln = names?.getOrNull(1)

        fn=emptyToNull(fn)
        ln=emptyToNull(ln)

        return Pair(fn, ln)

    }

    /**
     * возвращающий строку с первыми буквами имени и фамилии в верхнем регистре
     */
    fun toInitials(firstName: String?, lastName:String?):String? {
        var f = emptyToNull(firstName)?.substring(0, 1)?.toUpperCase()
        var l = emptyToNull(lastName)?.substring(0, 1)?.toUpperCase()
        return when {
            f == null && l == null -> null
            f == null -> l
            l ==null -> f
            else -> "${f}${l}"
        }
    }

    private fun emptyToNull(str: String?): String? {
        when{
            str.equals(null) || str!!.isBlank() -> return null
             else -> return str
        }
    }
}