package org.checkout.app.service

import java.time.LocalDateTime
import java.time.Month
import org.checkout.app.service.DateBasedRule
import org.checkout.app.model.Cart
import org.checkout.app.model.Item
import org.checkout.app.model.Category

class DateBasedRuleImpl(
    private val description: String,
    private val condition: (LocalDateTime) -> Boolean,
    private val discountPercent: Double
) : DateBasedRule {

    override fun matches(cart: Cart): Boolean {
        return condition(cart.createdAt)
    }

    override fun apply(cart: Cart): Double {
        if (matches(cart)) {
            val discount = cart.totalPrice * discountPercent / 100
            cart.totalPrice -= discount
            println("Applied Date-Based Rule: $description ($discountPercent% off)")
        }
        return cart.totalPrice
    }
}