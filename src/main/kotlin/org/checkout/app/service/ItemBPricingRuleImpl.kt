package org.checkout.app.service

import org.checkout.app.model.Cart
import org.checkout.app.model.Item

/**
 * Rule: For item "B"
 * - Single price: 50 cents
 * - 2 for 80 Cents
 */
class ItemBPricingRuleImpl : Rule<Cart> {

    override fun matches(cart: Cart): Boolean {
        // Applies only if there’s at least one item named "A"
        return cart.items.any { it.name == "B" && it.quantity > 0 }
    }

    override fun apply(cart: Cart): Double {
        var total = 0.0

        for (item in cart.items) {
            if (item.name == "b") {
                val groupsOfTwo = item.quantity / 2
                val remaining = item.quantity % 2
                total += groupsOfTwo * 0.80 + remaining * 0.50
            } else {
                // No special pricing for other items
                total += item.price * item.quantity
            }
        }

        return total
    }
}
