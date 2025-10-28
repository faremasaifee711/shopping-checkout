package org.checkout.app.service

import org.checkout.app.service.PriceRule

class PriceRuleImpl : PriceRule {
    override fun isPriceRule(item: String): Boolean {
        // Example: count matching dates
        return item.length > 0
    }

    override fun matches(item: String): Boolean {
        return true
    }

    override fun apply(item: String): Double {
        // Example: count matching dates
        return item.length.toDouble()
    }
}