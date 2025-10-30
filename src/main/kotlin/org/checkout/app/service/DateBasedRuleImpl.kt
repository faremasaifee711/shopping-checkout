package org.checkout.app.service

import java.time.LocalDateTime
import org.checkout.app.model.Cart

/**
 * Generic implementation for time-sensitive discounts (e.g. Black Friday 10% off).
 */
class DateBasedRuleImpl(
    private val itemName: String,
    private val description: String,
    private val condition: (LocalDateTime) -> Boolean,
    private val discountPercent: Double
) : DateBasedRule {
    /**
     * Checks if the cart contains at least one item matching the rule's product.
     *
     * @param cart The cart on which the rule is evaluated.
     * @return true if any item matches; false otherwise.
     */
    override fun matches(cart: Cart): Boolean {
        return condition(cart.createdAt)
    }

    /**
     * Applies the date-based pricing rule to the cart if applicable, updating the cart total price.
     *
     * @param cart The cart to update pricing on.
     * @return The updated total price after applying the discount.
     */
    override fun apply(cart: Cart): Double {
        var discount = 0.0
    
        // Find the item matching this rule
        val item = cart.cartItems.find { it.name.equals(itemName, ignoreCase = true) }
    
        if (item != null) {
            
            // Calculate price without discount
            val priceWithoutDiscount = item.price * item.quantity
            
            // Calculate price with multi-buy discount
            discount = priceWithoutDiscount  * discountPercent / 100
    
            println("Calculated discount from DateBasedRule: $description -> $discount")
        }
    
        return discount
    }
}