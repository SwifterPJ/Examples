package com.example.db.shared

import com.example.db.ExampleDb
import com.example.db.User
import com.example.db.UserQueries
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.TransacterImpl
import com.squareup.sqldelight.`internal`.copyOnWriteList
import com.squareup.sqldelight.db.SqlDriver
import kotlin.Any
import kotlin.Int
import kotlin.String
import kotlin.Unit
import kotlin.collections.MutableList
import kotlin.reflect.KClass

internal val KClass<ExampleDb>.schema: SqlDriver.Schema
  get() = ExampleDbImpl.Schema

internal fun KClass<ExampleDb>.newInstance(driver: SqlDriver): ExampleDb = ExampleDbImpl(driver)

private class ExampleDbImpl(
  driver: SqlDriver
) : TransacterImpl(driver), ExampleDb {
  public override val userQueries: UserQueriesImpl = UserQueriesImpl(this, driver)

  public object Schema : SqlDriver.Schema {
    public override val version: Int
      get() = 1

    public override fun create(driver: SqlDriver): Unit {
      driver.execute(null, """
          |CREATE TABLE user (
          |    UUID TEXT PRIMARY KEY,
          |    firstName TEXT,
          |    lastName TEXT
          |)
          """.trimMargin(), 0)
    }

    public override fun migrate(
      driver: SqlDriver,
      oldVersion: Int,
      newVersion: Int
    ): Unit {
    }
  }
}

private class UserQueriesImpl(
  private val database: ExampleDbImpl,
  private val driver: SqlDriver
) : TransacterImpl(driver), UserQueries {
  internal val getAll: MutableList<Query<*>> = copyOnWriteList()

  public override fun <T : Any> getAll(mapper: (
    UUID: String,
    firstName: String?,
    lastName: String?
  ) -> T): Query<T> = Query(2018756975, getAll, driver, "user.sq", "getAll", "SELECT * FROM user") {
      cursor ->
    mapper(
      cursor.getString(0)!!,
      cursor.getString(1),
      cursor.getString(2)
    )
  }

  public override fun getAll(): Query<User> = getAll { UUID, firstName, lastName ->
    User(
      UUID,
      firstName,
      lastName
    )
  }

  public override fun insert(user: User): Unit {
    driver.execute(2084331965, """INSERT INTO user VALUES (?, ?, ?)""", 3) {
      bindString(1, user.UUID)
      bindString(2, user.firstName)
      bindString(3, user.lastName)
    }
    notifyQueries(2084331965, {database.userQueries.getAll})
  }

  public override fun update(user: User): Unit {
    driver.execute(-1865689139, """INSERT OR REPLACE INTO user VALUES (?, ?, ?)""", 3) {
      bindString(1, user.UUID)
      bindString(2, user.firstName)
      bindString(3, user.lastName)
    }
    notifyQueries(-1865689139, {database.userQueries.getAll})
  }

  public override fun deleteAll(): Unit {
    driver.execute(2017192562, """DELETE FROM user""", 0)
    notifyQueries(2017192562, {database.userQueries.getAll})
  }
}
