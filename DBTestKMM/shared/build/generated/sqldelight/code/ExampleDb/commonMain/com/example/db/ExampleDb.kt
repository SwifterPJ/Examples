package com.example.db

import com.example.db.shared.newInstance
import com.example.db.shared.schema
import com.squareup.sqldelight.Transacter
import com.squareup.sqldelight.db.SqlDriver

public interface ExampleDb : Transacter {
  public val userQueries: UserQueries

  public companion object {
    public val Schema: SqlDriver.Schema
      get() = ExampleDb::class.schema

    public operator fun invoke(driver: SqlDriver): ExampleDb = ExampleDb::class.newInstance(driver)
  }
}
