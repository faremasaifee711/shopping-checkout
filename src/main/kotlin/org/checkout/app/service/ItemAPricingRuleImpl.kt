package org.checkout.app.service

import org.checkout.app.model.Cart
import org.checkout.app.model.Item

/**
 * Rule: For item "A"
 * - Single price: 40 cents
 * - 3 for 1 Euro
 */
class ItemAPricingRuleImpl : Rule<Cart> {

    override fun matches(cart: Cart): Boolean {
        // Applies only if there’s at least one item named "A"
        return cart.items.any { it.name == "A" && it.quantity > 0 }
    }

    override fun apply(cart: Cart): Double {
        var total = 0.0

        for (item in cart.items) {
            if (item.name == "A") {
                val groupsOfThree = item.quantity / 3
                val remaining = item.quantity % 3
                total += groupsOfThree * 1 + remaining * 0.4
            } else {
                // No special pricing for other items
                total += item.price * item.quantity
            }
        }

        return total
    }
}
