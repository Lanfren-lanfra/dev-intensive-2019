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

    private fun emptyToNull(str: String?): String? {
        when{
            str.equals(null) || str!!.isEmpty() -> return null
             else -> return str
        }
    }
}