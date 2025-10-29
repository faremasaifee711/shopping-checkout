package org.checkout.app.model

import java.time.LocalDateTime


import org.checkout.app.model.Item
import org.checkout.app.model.Customer
import org.checkout.app.service.PricingRules
import org.checkout.app.service.CartRule

data class Cart(
    val items: MutableList<Item> = mutableListOf(),
    val customer: Customer,
    var totalPrice: Double = 0.0,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var rules : List<CartRule> = listOf(),
) {

    init {
        this.rules = listOf(
            CartRuleFactory.create(CartRuleType.ITEM_A),
            CartRuleFactory.create(CartRuleType.ITEM_B),
            CartRuleFactory.create(CartRuleType.BLACK_FRIDAY)
        )
    }

    fun addItem(item: Item, pricingRules: List<PricingRule>) {
        val existingItemIndex = items.indexOfFirst { it.name.equals(item.name, ignoreCase = true) }

        if (existingItemIndex != -1) {
            val existingItem = items[existingItemIndex]
            val newQuantity = existingItem.quantity + item.quantity
            items[existingItemIndex] = existingItem.copy(quantity = newQuantity)
        } else {
            items.add(item)
        }

        calculateCartlPrice()
    }

    fun removeItem(item: Item) {
        items.remove(item)
        calculateCartlPrice()
    }

    fun calculateCartlPrice() {
        this.totalPrice = items.sumOf { it.totalCostAfterTax() }
        for (rule in this.rules) {
            if (rule.matches(this)) {
                this.totalPrice = rule.apply(this)
            }
        }
    }
    
    override fun toString(): String {
        return "Cart(items=$items, customer=$customer, totalPrice=$totalPrice, createdAt=$createdAt)"
    }
}