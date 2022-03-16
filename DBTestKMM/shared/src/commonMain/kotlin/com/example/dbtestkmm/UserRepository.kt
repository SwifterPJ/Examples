package com.example.dbtestkmm

import com.example.db.ExampleDb
import com.example.db.User

class UserRepository(private val db: ExampleDb) {
    fun create(user: User) = db.userQueries.insert(user)

    fun update(user: User) = db.userQueries.update(user)

    fun getAll(): List<User> = db.userQueries.getAll().executeAsList()

    fun deleteAll() = db.userQueries.deleteAll()
}