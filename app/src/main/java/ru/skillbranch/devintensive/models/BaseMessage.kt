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
    abstract fun formatMessage(): String

    companion object MessageFactory {
        var lastId = -1;

        fun makeMessage(
            from: User,
            chat: Chat,
            date: Date,
            payload: Any,
            type: String,
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