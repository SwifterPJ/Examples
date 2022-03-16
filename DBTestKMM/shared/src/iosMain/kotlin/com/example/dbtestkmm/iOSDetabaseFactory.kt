package com.example.db.database

import co.touchlab.sqliter.DatabaseConfiguration
import co.touchlab.sqliter.DatabaseFileContext
import com.example.database.ExampleSchema
import com.example.db.ExampleDb
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver
import com.squareup.sqldelight.drivers.native.wrapConnection

class iOSDatabaseFactory(): DatabaseFactory {

    override fun createDatabase(): ExampleDb {
        val name = "test.db"

        DatabaseFileContext.deleteDatabase(name, null)
        println("DB deleted")
        val db = createDatabase(name)
        println("DB created")
        return db
    }

    private fun createDatabase(name: String): ExampleDb {
        val schema = ExampleSchema()
        val encryption = DatabaseConfiguration.Encryption("f0723a359698301c8e32d238fd5b847906d9e0b90e4cc174030c29972be606fd")
        val config = DatabaseConfiguration(
            name = name,
            version = schema.version,
            create = { connection ->
                wrapConnection(connection) { schema.create(it) }
            },
            encryptionConfig = encryption
       )

        val driver = NativeSqliteDriver(config)

        return ExampleDb(driver)
    }

}