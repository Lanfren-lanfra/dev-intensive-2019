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
        fun makeMessage(
            from: User,
            chat: Chat,
            date: Date,
            type: String,
            payload: Any,
            isIncoming: Boolean = false
        ): String {

            return """${from?.firstName}
                    ${if (isIncoming) "получил" else "отправил"}
                    ${if (type == "text") "сообщение" else "изображение"}
                    ${when (payload) {
                            is TextMessage -> payload.text
                            else -> (payload as ImageMessage).url
                        }
                    }
                    ${//TODO date format
                            "" }
                    """

        }
    }
}