package org.checkout.app.model

import java.time.LocalDateTime

import org.checkout.app.data.CartRuleFactory
import org.checkout.app.data.CartRuleType
import org.checkout.app.model.Item
import org.checkout.app.model.Customer
import org.checkout.app.service.PricingRules
import org.checkout.app.service.CartRule

/**
 * The shopping cart which holds items selected by the customer.
 * Responsible for managing items, updating quantities, and calculating the total price.
 *
 * @property items The mutable list of items currently in the cart.
 * @property customer The customer associated with this cart.
 * @property totalPrice The current total price of the cart, accounting for discounts and pricing rules.
 * @property createdAt The date and time when the cart is created
 * @property rules The Pricing rules which can determine discounts to be applied on total price of cart
 */
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

    /**
     * Adds an item to the cart. If the item already exists, updates the quantity and recalculates price based on pricing rules.
     *
     * @param item The item to add or update in the cart.
     * @param pricingRules List of pricing rules to apply for pricing adjustments and discounts.
     */
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

    /**
     * Calculates the total price of the cart by summing the price * quantity for all items.
     * Applies pricing rules on the cart
     * Should be called whenever the cart's content changes to keep the total price accurate.
     */
    fun calculateCartlPrice() {
        this.totalPrice = items.sumOf { it.totalCostAfterTax() }
        this.applyPricingRules()
    }

    fun applyPricingRules() {
        val baseTotal = items.sumOf { it.price * it.quantity }
        val totalDiscount = this.rules.filter { it.matches(this) }
            .sumOf { it.apply(this) }
        this.totalPrice = baseTotal - totalDiscount
    }
    
    override fun toString(): String {
        return "Cart(items=$items, customer=$customer, totalPrice=$totalPrice, createdAt=$createdAt)"
    }
}