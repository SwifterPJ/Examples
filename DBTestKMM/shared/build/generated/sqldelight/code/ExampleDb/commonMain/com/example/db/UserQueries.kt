package com.example.db

import com.squareup.sqldelight.Query
import com.squareup.sqldelight.Transacter
import kotlin.Any
import kotlin.String
import kotlin.Unit

public interface UserQueries : Transacter {
  public fun <T : Any> getAll(mapper: (
    UUID: String,
    firstName: String?,
    lastName: String?
  ) -> T): Query<T>

  public fun getAll(): Query<User>

  public fun insert(user: User): Unit

  public fun update(user: User): Unit

  public fun deleteAll(): Unit
}
