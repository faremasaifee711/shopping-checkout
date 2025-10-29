package org.checkout.app.service

import org.checkout.app.service.MultiBuyRule
import org.checkout.app.model.Cart

/**
 * Generic implementation of quantity-based pricing (e.g. 3 for 1Euro).
 */
class MultiBuyRuleImpl(
    private val itemName: String,
    private val groupSize: Int,
    private val groupPrice: Double,
    private val unitPrice: Double,
    private val description: String
) : MultiBuyRule {
    override fun matches(cart: Cart): Boolean {
        return cart.items.any { it.name.equals(itemName, ignoreCase = true) && it.quantity > 0 }
    }

    override fun apply(cart: Cart): Double {
        // Start with the current total of the cart before this rule
        var total = cart.totalPrice

        // Find the item matching the rule
        val item = cart.items.find { it.name.equals(itemName, ignoreCase = true) }

        if (item != null && item.quantity > 0) {
            val groups = item.quantity / groupSize
            val remaining = item.quantity % groupSize
            
            // Calculate the total price for the matched item according to the rule
            val itemTotalWithRule = groups * groupPrice + remaining * unitPrice
            
            // Calculate the total price for that item without the rule
            val itemTotalWithoutRule = item.price * item.quantity
            
            // Adjust the overall cart total: remove old item total, add new discounted total
            total = total - itemTotalWithoutRule + itemTotalWithRule
            
            println("Applied MultiBuy Rule: $description and adjusted total is $total")
        }

        cart.totalPrice = total
        return total
    }
}