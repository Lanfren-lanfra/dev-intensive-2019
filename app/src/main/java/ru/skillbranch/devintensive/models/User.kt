package ru.skillbranch.devintensive.models

import java.util.*

data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    var lastVisit: Date? = Date(),
    var isOnline: Boolean = false
) {

    companion object UserFactory {

        var idCounter = 0
        fun makeUser(fullName: String): User {
            var (fn, ln) = fullName.split(" ")
            return User("${++idCounter}", fn, ln, null)
        }
    }


    class Builder {
        var user: User

        init {
            user = User("", null, null, null)
        }

        fun id(s: String): Builder {
            user = user.copy(id = s)
            return this
        }

        fun firstName(s: String): Builder {
            user.firstName = s
            return this
        }

        fun lastName(s: String): Builder {
            user.lastName = s
            return this
        }

        fun avatar(s: String): Builder {
            user.avatar = s
            return this
        }

        fun rating(n: Int): Builder {
            user.rating = n
            return this
        }

        fun respect(n: Int): Builder {
            user.respect = n
            return this
        }

        fun lastVisit(d: Date): Builder {
            user.lastVisit = d
            return this
        }

        fun isOnline(b: Boolean): Builder {
            user.isOnline = b
            return this
        }

        fun build(): User {
            return user
        }
    }
}