package org.checkout.app.service

import org.checkout.app.service.PriceRule

class PriceRuleImpl : PriceRule {
    override fun isPriceRule(item: String): Boolean {
        // Example: count matching dates
        return true
    }

    override fun matches(items: List<String>): Boolean {
        return items.size >= 3
    }

    override fun apply(items: List<String>): Double {
        // Example: count matching dates
        return items.count { isPriceRule(it) }.toDouble()
    }
}