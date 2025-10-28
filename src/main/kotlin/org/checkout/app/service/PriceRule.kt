package org.checkout.app.service

import java.time.LocalDate
import org.checkout.app.service.Rule

interface PriceRule : Rule<String> {
    fun isPriceRule(item: String): Boolean
}


