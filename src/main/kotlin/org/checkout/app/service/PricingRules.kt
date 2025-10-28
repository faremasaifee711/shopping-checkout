package org.checkout.app.service

import java.time.LocalDate
import org.checkout.app.service.CartRule

class PricingRules (
    val cartRules: MutableList<CartRule> = mutableListOf()
) {
    fun addRule(cartRule: CartRule) {
        cartRules.add(cartRule)
    }
}


