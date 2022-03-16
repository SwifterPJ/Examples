package com.example.db.database

import com.example.db.ExampleDb

interface DatabaseFactory {
    fun createDatabase(): ExampleDb
}
