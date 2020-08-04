package com.example.rxjavauseage

data class User(var userName: String, var password: String) {
    var list: List<Course>? = null
}

data class Course(var id: String, var name: String)

object UserDataUtil {
    fun mock(): User {
        val list = mutableListOf<Course>()
        list.add(Course("id1", "name1"))
        list.add(Course("id2", "name2"))
        list.add(Course("id3", "name3"))
        val user = User("userName", "password")
        user.list = list
        return user
    }
}
