package ru.skillbranch.devintensive.models

import java.util.*

data class User (
    val id : String,
    var firstName : String?,
    var lastName : String?,
    var avatar : String?,
    var rating : Int = 0,
    var respect : Int = 0,
    var lastVisit : Date? = Date(),
    var isOnline : Boolean = false
){

    companion object UserFactory{

        var idCounter = 0
        fun makeUser(fullName: String): User{
            var (fn, ln) = fullName.split(" ")
            return User("${++idCounter}", fn, ln, null)
        }
    }
}