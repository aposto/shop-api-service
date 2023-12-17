package org.shop.shopapiservice.domain

data class CommandResult(val error: String? = null, val success: Boolean = error == null)

fun commandOk() = CommandResult()

fun commandFail(e: String) = CommandResult(e)

data class CommandBodyResult<T>(val body: T? = null, val error: String? = null, val success: Boolean = error == null)

fun <T> commandBodyOk(body: T) = CommandBodyResult(body)

fun <T> commandBodyFail(error: String) = CommandBodyResult<T>(null, error)
