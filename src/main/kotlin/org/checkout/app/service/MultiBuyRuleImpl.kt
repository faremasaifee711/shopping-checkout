package org.checkout.app.service

import org.checkout.app.service.MultiBuyRule
import org.checkout.app.model.Cart

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
        var total = 0.0

        for (item in cart.items) {
            if (item.name.equals(itemName, ignoreCase = true)) {
                val groups = item.quantity / groupSize
                val remaining = item.quantity % groupSize
                total += groups * groupPrice + remaining * unitPrice
            } else {
                total += item.price * item.quantity
            }
        }

        println("Applied MultiBuy Rule: $description")
        cart.totalPrice = total
        return total
    }
}