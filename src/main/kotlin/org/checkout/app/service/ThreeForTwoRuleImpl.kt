package org.checkout.app.service

import org.checkout.app.service.MultiBuyRule

class ThreeForTwoRuleImpl : MultiBuyRule {
    override fun matches(items: List<String>): Boolean {
        return items.size >= 3
    }

    override fun apply(items: List<String>): Double {
        return items.size.toDouble()
    }
}