package org.checkout.app.service

class RuleEngine(
    val cartRules: MutableList<CartRule> = mutableListOf()
) {
    fun addRule(cartRule: CartRule) {
        cartRules.add(cartRule)
    }
}