package org.checkout.app.service

import org.checkout.app.service.MultiBuyRule
import org.checkout.app.model.Cart

class ThreeForTwoRuleImpl : MultiBuyRule {
    override fun matches(cart: Cart): Boolean {
        // Example: return true if any date matches Black Friday
        return true
    }

    override fun apply(cart: Cart): Double {
        // Example: count matching dates
        return cart.items.size.toDouble()
    }
}