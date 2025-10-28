package org.checkout.app.service

import java.time.LocalDate
import org.checkout.app.service.Rule

interface DateBasedRule : Rule<LocalDate> {
    fun isBlackFriday(date: LocalDate): Boolean
}

