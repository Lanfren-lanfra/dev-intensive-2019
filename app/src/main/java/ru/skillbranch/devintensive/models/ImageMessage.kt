package ru.skillbranch.devintensive.models

import java.util.*

class ImageMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean,
    date: Date,
    var url: String

):BaseMessage(id, from , chat, isIncoming, date) {
    override fun formatMessage(id: String, name: String, mode: String, type: String): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}