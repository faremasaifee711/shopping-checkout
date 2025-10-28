package org.checkout.app.service

import java.time.LocalDate
import java.time.Month
import org.checkout.app.service.DateBasedRule

class BlackFridayRuleImpl(private val desc: String) : DateBasedRule {

    override fun isBlackFriday(date: LocalDate): Boolean {
        return date.month == Month.NOVEMBER && date.dayOfMonth == 28
    }

    override fun matches(items: List<LocalDate>): Boolean {
        // Example: return true if any date matches Black Friday
        return items.any { isBlackFriday(it) }
    }

    override fun apply(items: List<LocalDate>): Double {
        // Example: count matching dates
        return items.count { isBlackFriday(it) }.toDouble()
    }
}