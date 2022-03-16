package com.example.database

import com.example.db.ExampleDb
import com.squareup.sqldelight.db.SqlDriver

class ExampleSchema(): SqlDriver.Schema by ExampleDb.Schema {}
