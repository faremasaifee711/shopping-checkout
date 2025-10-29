package org.checkout.app.service

interface Rule<T> {
    /**
     * Determines if the rule applies to the given cart.
     */
    fun matches(cart: T): Boolean
    /**
     * Modifies the cart total or item prices according to the rule logic
     */
    fun apply(cart: T): Double
}


