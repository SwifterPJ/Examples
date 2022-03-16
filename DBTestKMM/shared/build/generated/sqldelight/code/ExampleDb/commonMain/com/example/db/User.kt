package com.example.db

import kotlin.String

public data class User(
  public val UUID: String,
  public val firstName: String?,
  public val lastName: String?
) {
  public override fun toString(): String = """
  |User [
  |  UUID: $UUID
  |  firstName: $firstName
  |  lastName: $lastName
  |]
  """.trimMargin()
}
