package ru.skillbranch.devintensive.extensions

/**
 * усекающий исходную строку до указанного числа символов
 * (по умолчанию 16) и возвращающий усеченную строку с заполнителем "..."
 * (если строка была усечена) если последний символ усеченной строки является
 * пробелом - удалить его и добавить заполнитель
 */
fun String.truncate(n:Int=16):String? {
    if (this==null) return null

    if (this.length < n || n<0){
        return this
    }

    var modified = this.substring(0,n)
    modified = modified.trimEnd()

    return    if (this.substring(n).isBlank()) modified else modified.plus("...")
}
