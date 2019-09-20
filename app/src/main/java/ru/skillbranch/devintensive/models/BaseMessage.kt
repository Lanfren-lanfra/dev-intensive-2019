package ru.skillbranch.devintensive.models

import java.util.*


abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false,
    val date: Date = Date()
) {

    /**
     * возвращает строку содержащюю информацию о id сообщения, имени получателя/отправителя,
     *  виде сообщения ("получил/отправил") и типе сообщения ("сообщение"/"изображение")
     */
    abstract fun formatMessage(id: String, name: String, mode: String, type: String): String

    companion object MessageFactory {
        var lastId = -1;

        fun makeMessage(
            from: User,
            chat: Chat,
            date: Date,
            type: String,
            payload: Any,
            isIncoming: Boolean = false
        ): BaseMessage {

            lastId++
            return when (type) {
                "text" -> TextMessage("${lastId}", from, chat,isIncoming, date, payload as String)
                else -> ImageMessage("${lastId}", from, chat,isIncoming, date, payload as String)
            }

        }
    }
}