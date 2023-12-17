package org.shop.shopapiservice.adapter.persistence

import org.shop.shopapiservice.domain.CommandResult
import org.shop.shopapiservice.domain.commandFail
import org.shop.shopapiservice.domain.commandOk
import org.springframework.dao.DuplicateKeyException

suspend fun <T> handleModifyError(f: suspend () -> T): CommandResult {
    try {
        f()
        return commandOk()
    } catch (dup: DuplicateKeyException) {
        return commandFail("DUPLICATED")
    } catch (t: Throwable) {
        return commandFail(t.message ?: "INTERNAL_SERVER")
    }
}
