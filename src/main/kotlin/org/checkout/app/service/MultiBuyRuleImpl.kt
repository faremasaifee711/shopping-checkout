package org.checkout.app.service

import org.checkout.app.service.MultiBuyRule
import org.checkout.app.model.Cart

/**
 * Implementation of a multi-buy pricing rule where customers get a fixed price for a group purchase.
 *
 * @property itemName The product name the rule applies to.
 * @property groupSize The number of items needed for the group price to apply.
 * @property groupPrice The fixed total price for the group.
 * @property unitPrice The standard unit price.
 * @property description A human-readable description of the rule for logging or display.
 */
class MultiBuyRuleImpl(
    private val itemName: String,
    private val groupSize: Int,
    private val groupPrice: Double,
    private val unitPrice: Double,
    private val description: String
) : MultiBuyRule {
    /**
     * Checks if the cart contains at least one item matching the rule's product.
     *
     * @param cart The cart on which the rule is evaluated.
     * @return true if any item matches; false otherwise.
     */
    override fun matches(cart: Cart): Boolean {
        return cart.items.any { it.name.equals(itemName, ignoreCase = true) && it.quantity > 0 }
    }

    /**
    * Applies the multi-buy pricing rule logic on the cart and returns the discount amount.
    * Does NOT mutate the cart directly.
    *
    * @param cart The cart to which the rule would be applied.
    * @return The discount amount calculated from applying this rule.
    */
    override fun apply(cart: Cart): Double {
        var discount = 0.0
    
        // Find the item matching this rule
        val item = cart.items.find { it.name.equals(itemName, ignoreCase = true) }
    
        if (item != null && item.quantity >= groupSize) {
            val groups = item.quantity / groupSize
            val remaining = item.quantity % groupSize
            
            // Calculate price without discount
            val priceWithoutDiscount = item.price * item.quantity
            
            // Calculate price with multi-buy discount
            val priceWithDiscount = groups * groupPrice + remaining * unitPrice
            
            // Discount is the difference
            discount = priceWithoutDiscount - priceWithDiscount
    
            println("Calculated discount from MultiBuyRule: $description -> $discount")
        }
    
        return discount
    }
}